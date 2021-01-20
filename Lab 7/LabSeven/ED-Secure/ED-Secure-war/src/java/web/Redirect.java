package web;

import java.io.IOException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author elau
 */
@Named(value = "Redirect")
@SessionScoped
public class Redirect extends HttpServlet {

    /**
     * Creates a new instance of MyLoginManagedBean
     */
    public Redirect() {
    }

    public void mainmenu() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        if (request.isUserInRole("ED-APP-ADMIN")) {
            context.getExternalContext().redirect("faces/admin/mainmenu.xhtml");
        } else if (request.isUserInRole("ED-APP-USERS")) {
            context.getExternalContext().redirect("faces/user/mainmenu.xhtml");
        }
    }
}