
package ed.jdbc;

import java.util.ArrayList;
import java.util.Scanner;


public class SetUpMyUser {

    public static void main(String[] args) {
        MyDB mydb = new MyDB();

        System.out.println("Welcome to the database");
        while (true) {
            System.out.println("1. Check current record\n" +
                                "2. Create a record\n" +
                                "3. Update a user\n" +
                                "4. Delete a record\n" +
                                "5. Exit");
            Scanner option1 = new Scanner(System.in);
            int choice = Integer.parseInt(option1.nextLine());

            if (choice == 1) {
                Scanner option2 = new Scanner(System.in);
                System.out.println("Enter User ID");
                String userID = option2.nextLine();
                Myuser user = mydb.getRecord(userID);
                System.out.println("Name: " + user.getName() + "\n" +
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

                Myuser newUser = new Myuser(userID, name, password, email, phone, address, secqn, secans);
                mydb.createRecord(newUser);
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

                Myuser updatedUser = new Myuser(userID, name, password, email, phone, address, secqn, secans);
                mydb.updateRecord(updatedUser);
            } else if (choice == 4) {

                Scanner s = new Scanner(System.in);
                System.out.println("Enter UserID to Delete");
                String userID = s.nextLine();
                mydb.deleteRecord(userID);

            } else if (choice == 5) {
                System.exit(1);
            } else {
                System.out.println("Invalid Selection");
            }
        }

    }

    public static ArrayList<Myuser> prepareMyuserData() { ArrayList<Myuser> myList = new ArrayList<Myuser>();
        Myuser myuser1 = new Myuser("000001", "Peter Smith", "123456", "psmith@swin.edu.au", "9876543210", "Swinburne EN510f", "What is my name?", "Peter");
        Myuser myuser2 = new Myuser("000002", "James T. Kirk", "234567", "jkirk@swin.edu.au", "8765432109", "Swinburne EN511a", "What is my name?", "James");
        Myuser myuser3 = new Myuser("000003", "Sheldon Cooper", "345678", "scooper@swin.edu.au", "7654321098", "Swinburne EN512a", "What is my last name?", "Cooper");
        Myuser myuser4 = new Myuser("000004", "Clark Kent", "456789", "ckent@swin.edu.au", "6543210987", "Swinburne EN513a", "What is my last name?", "Kent");
        Myuser myuser5 = new Myuser("000005", "Harry Potter", "567890", "hpotter@swin.edu.au", "6543210987", "Swinburne EN514a", "What is my last name?", "Potter");
        myList.add(myuser1); 
        myList.add(myuser2); 
        myList.add(myuser3); 
        myList.add(myuser4); 
        myList.add(myuser5);
        return myList;
    }
}