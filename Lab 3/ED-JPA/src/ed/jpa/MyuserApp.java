package ed.jpa;


import java.util.Scanner;

public class MyuserApp {

    private MyuserDB mydb;
    public MyuserApp() {
        mydb = new MyuserDB();
    }
    public static void main(String[] args) {
        MyuserApp client = new MyuserApp();
        
        System.out.println("Welcome to the database");
        while (true) {
            System.out.println("1. Check current record\n" +
                                "2. Create a record\n" +
                                "3. Update a user\n" +
                                "4. Delete a record\n" +
                                "5. Exit");
            Scanner myObj = new Scanner(System.in);
            int choice = Integer.parseInt(myObj.nextLine());

            if (choice == 1) {
                Scanner myObj2 = new Scanner(System.in);
                System.out.println("Enter User ID");
                String userID = myObj2.nextLine();
                
                MyuserDTO user = client.getRecord(userID);
                System.out.println( "Name: " + user.getName() + "\n" +
                                    "Password: " + user.getPassword() + "\n" +
                                    "Email: " + user.getEmail() + "\n" +
                                    "Phone: " + user.getPhone() + "\n" +
                                    "Address: " + user.getAddress() + "\n" +
                                    "SecQn: " + user.getSecQn() + "\n" +
                                    "SecAns: " + user.getSecAns() + "\n"
                                    );

            } else if (choice == 2) {
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
            } else if (choice == 3) {
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
            } else if (choice == 4) {

                Scanner s = new Scanner(System.in);
                System.out.println("Enter UserID to Delete");
                String userID = s.nextLine();
                client.deleteRecord(userID);

            } else if (choice == 5) {
                System.exit(1);
            } else {
                System.out.println("Invalid Selection");
            }
        }
                  
    }
    public void showCreateResult(boolean result, MyuserDTO myuserDTO) {
        if (result) {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " has been created in the database table.");
        } else {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " could not be created in the database table!");
        }
    }
    public boolean createRecord(MyuserDTO myuserDTO) {
        return mydb.createRecord(myuserDTO);
    }
    
    public MyuserDTO getRecord(String userId) {
        return mydb.getRecord(userId);
    }
    
    public boolean updateRecord(MyuserDTO myuserDTO) {
        return mydb.updateRecord(myuserDTO);
    }

    public boolean deleteRecord(String userId) {
        return mydb.deleteRecord(userId);
    }
}