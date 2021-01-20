package web;

import entity.MyuserDTO;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import session.MyuserFacadeRemote;

@Named(value = "myuserManagedBean")
@RequestScoped
public class MyuserManagedBean {

    @EJB
    private MyuserFacadeRemote myuserFacade;

    private String userid;
    private String name;
    private String password;
    private String cPassword; // for confirmed password field private String email;
    private String email;
    private String phone;
    private String address;
    private String secQn;
    private String secAns;
    private String oldEmail;

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    /**
     * Creates a new instance of MyuserManagedBean
     */
    public MyuserManagedBean() {

    }

    public MyuserFacadeRemote getMyuserFacade() {
        return myuserFacade;
    }

    public void setMyuserFacade(MyuserFacadeRemote myuserFacade) {
        this.myuserFacade = myuserFacade;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSecQn() {
        return secQn;
    }

    public void setSecQn(String secQn) {
        this.secQn = secQn;
    }

    public String getSecAns() {
        return secAns;
    }

    public void setSecAns(String secAns) {
        this.secAns = secAns;
    }

    public String getRecord() {

        String result = "failure";

        if (isValidUserid(userid)) {
            MyuserDTO myuserDTO = myuserFacade.getRecord(userid);

            if (myuserDTO != null) {
                setUserid(myuserDTO.getUserid());
                setAddress(myuserDTO.getAddress());
                setEmail(myuserDTO.getEmail());
                setName(myuserDTO.getName());
                setPassword(myuserDTO.getPassword());
                setPhone(myuserDTO.getPhone());
                setSecAns(myuserDTO.getSecAns());
                setSecQn(myuserDTO.getSecQn());

                setOldEmail(myuserDTO.getEmail());
                result = "success";
            }
        }
        return result;
    }

    /*
* add a user to the database
* @return "success" if the add operation is successful * "failure" otherwise
     */
    public String addUser() {
        String result = "failure";
        /*
* are all data entered valid?
* and password the same as cPassword (case sensitive)
* before calling the façade’s createRecord() method */

        System.out.println("Add User Password: " + password);
        if (isValidUserid(userid) && isValidName(name)
        && isValidPassword(password) && isValidPassword(cPassword)
        && isValidEmail(email) && isValidPhone(phone)
        && isValidAddress(address) && isValidSecQn(secQn)
        && isValidSecAns(secAns) && password.equals(cPassword)) {
            MyuserDTO myuserDTO = new MyuserDTO(userid, name, password, email, phone, address, secQn, secAns);
            if (myuserFacade.createRecord(myuserDTO)) {
                result = "success";
            }
        }
        return result;
    }

    /*
* add a user to the database
* @return "success" if the add operation is successful * "failure" otherwise
     */
    public String updateUser() {
        String result = "failure";

        boolean passwordsMatch = validatePasswordCorrect();
        if (!passwordsMatch) {
            return result;
        }

        if (isValidUserid(userid) && isValidName(name)
        && isValidPassword(password) && isValidPassword(cPassword)
        && isValidEmail(email) && isValidPhone(phone)
        && isValidAddress(address) && isValidSecQn(secQn)
        && isValidSecAns(secAns) && password.equals(cPassword)) {
            MyuserDTO myuserDTO = new MyuserDTO(userid, name, password, email, phone, address, secQn, secAns);
            if (myuserFacade.updateRecord(myuserDTO)) {
                result = "success";
            }
            System.out.println("Result: " + result);

        }

        if ("success".equals(result)) {
            MailCheck.sendMail(getOldEmail());
        }
        return result;
    }

    public boolean validatePasswordCorrect() {

        boolean result = false;

        if (isValidPassword(password)) {
            if (password.equals(cPassword)) {
                System.out.println("Passwords need to match");
                result = true;
            }
        }
        return result;
    }

    /* Some basic checking, complicated checking can be done later * not a good way of doing this
    * Should use JSF’s validator method to do this – left as C task
     */
    public boolean isValidUserid(String userid) {
        return (userid != null);
    }

    public boolean isValidName(String name) {
        return (name != null);
    }

    public boolean isValidPassword(String password) {
        return (password != null);
    }

    public boolean isValidEmail(String email) {
        return (email != null);
    }

    public boolean isValidPhone(String phone) {
        return (phone != null);
    }

    public boolean isValidAddress(String address) {
        return (address != null);
    }

    public boolean isValidSecQn(String secQn) {
        return (secQn != null);
    }

    public boolean isValidSecAns(String secAns) {
        return (secAns != null);
    }

    public void validatePasswordCorrect(FacesContext context, UIComponent component,
    Object value) {

        // Retrieve the value passed to this method
        String confirmPassword = (String) value;

        // Retrieve the temporary value from the password field
        UIInput passwordInput = (UIInput) component.findComponent("password");
        String password = (String) passwordInput.getLocalValue();

        System.out.println("Password input: " + password);

        if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            String message = context.getApplication().evaluateExpressionGet(context, "#{msgs['nomatch']}", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords need to match!", "Passwords need to match!");

            System.out.println("PASSWORD NOT MATCHED");
            throw new ValidatorException(msg);
        }
    }
}