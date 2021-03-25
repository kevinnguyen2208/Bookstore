package edjee;

import entity.MyuserDTO;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ejb.EJB;
import session.MyuserFacadeRemote;


public class MyuserAppClient {

    @EJB
    private static MyuserFacadeRemote myuserFacade;

    /**
     * @param args the command line arguments
     */
    public MyuserAppClient() {
    }

    public static void main(String[] args) {
        MyuserAppClient client = new MyuserAppClient();
        // assuming inputs from keyboard or any GUI
        System.out.println("Welcome to the database");
        while (true) {
            System.out.println("1. Check current record\n"
            + "2. Create a record\n"
            + "3. Update a user\n"
            + "4. Delete a record\n"
            + "5. Get Records by address\n"
            + "6. Exit");
            Scanner myObj = new Scanner(System.in);
            int userOption = Integer.parseInt(myObj.nextLine());

            if (userOption == 1) {
                Scanner myObj2 = new Scanner(System.in);
                System.out.println("Enter User ID");
                String userID = myObj2.nextLine();

                MyuserDTO user = client.getRecord(userID);
                System.out.println("Name: " + user.getName() + "\n"
                + "Password: " + user.getPassword() + "\n"
                + "Email: " + user.getEmail() + "\n"
                + "Phone: " + user.getPhone() + "\n"
                + "Address: " + user.getAddress() + "\n"
                + "SecQn: " + user.getSecQn() + "\n"
                + "SecAns: " + user.getSecAns() + "\n"
                );

            } else if (userOption == 2) {
                Scanner s = new Scanner(System.in);

                System.out.println("Enter UserID");
                String userID = s.nextLine();

                System.out.println("Name: ");
                String name = s.nextLine();

                System.out.println("Password: ");
                String password = s.nextLine();

                System.out.println("Email: ");
                String email = s.nextLine();

                System.out.println("Phone: ");
                String phone = s.nextLine();

                System.out.println("Address: ");
                String address = s.nextLine();

                System.out.println("Secqn:");
                String secqn = s.nextLine();

                System.out.println("Secans:");
                String secans = s.nextLine();

                MyuserDTO newUser = new MyuserDTO(userID, name, password, email, phone, address, secqn, secans);
                client.createRecord(newUser);
            } else if (userOption == 3) {
                Scanner s = new Scanner(System.in);

                System.out.println("Enter UserID to Update");
                String userID = s.nextLine();

                System.out.println("Name: ");
                String name = s.nextLine();

                System.out.println("Password: ");
                String password = s.nextLine();

                System.out.println("Email: ");
                String email = s.nextLine();

                System.out.println("Phone: ");
                String phone = s.nextLine();

                System.out.println("Address: ");
                String address = s.nextLine();

                System.out.println("Secqn:");
                String secqn = s.nextLine();

                System.out.println("Secans:");
                String secans = s.nextLine();

                MyuserDTO updatedUser = new MyuserDTO(userID, name, password, email, phone, address, secqn, secans);
                client.updateRecord(updatedUser);
            } else if (userOption == 4) {

                Scanner s = new Scanner(System.in);
                System.out.println("Enter UserID to Delete");
                String userID = s.nextLine();
                client.deleteRecord(userID);

            } else if (userOption == 5) {
                Scanner s = new Scanner(System.in);
                System.out.println("Enter address");
                String address = s.nextLine();
                ArrayList<MyuserDTO> myuserList = client.getRecordsByAddress(address);

                for (int i = 0; i < myuserList.size(); i++) {
                    System.out.println(myuserList.get(i).getName());
                }
            } else if (userOption == 6) {
                System.exit(1);
            } else {
                System.out.println("Invalid Selection");
            }
        }
    }

    public void showCreateResult(boolean result, MyuserDTO myuserDTO) {
        if (result) {
            System.out.println("Record with primary key " + myuserDTO.getUserid() + " has been created in the database table.");
        } else {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
            + " could not be created in the database table!");
        }
    }

    public Boolean createRecord(MyuserDTO myuserDTO) {
        return myuserFacade.createRecord(myuserDTO);
    }

    public MyuserDTO getRecord(String userId) {
        return myuserFacade.getRecord(userId);
    }

    public boolean updateRecord(MyuserDTO myuserDTO) {
        System.out.println("Updating record");
        return myuserFacade.updateRecord(myuserDTO);
    }

    public boolean deleteRecord(String userId) {
        return myuserFacade.deleteRecord(userId);
    }

    public ArrayList<MyuserDTO> getRecordsByAddress(String address) {
        return myuserFacade.getRecordsByAddress(address);
    }
}
