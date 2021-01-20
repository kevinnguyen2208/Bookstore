/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.util.ArrayList;

/**
 *
 * @author elau
 */
public class SetupEmployeeDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // the database object to access the actual database
        EmployeeDB db = new EmployeeDB();

        // make sure no name conflicts
        try {
            db.destroyDBTable();
        } catch (Exception ex) {
        }

        // create the database table
        System.out.println("Create an empty database table Employee");
        db.createDBTable();
        
        System.out.println("Add several static records in the database table");
        
        // prepare data
        Employee emp001 = new Employee("00001", "Adam", "1234567890", "1 John Street, Hawthorn",
                "adam@secure.com.au", "11111111", "ED-APP-ADMIN", "098-765432-1", 50000.0, true);
        Employee emp002 = new Employee("00002", "Bill", "2345678901", "2 Paul Street, Hawthorn",
                "bill@secure.com.au", "22222222", "ED-APP-ADMIN", "109-876543-2", 65000.0, true);
        Employee emp003 = new Employee("00003", "Ceci", "3456789012", "3 Mary Street, Hawthorn",
                "ceci@secure.com.au", "33333333", "ED-APP-USERS", "210-987654-3", 75000.0, true);
        Employee emp004 = new Employee("00004", "Dave", "4567890123", "4 Pete Street, Hawthorn", 
                "dave@secure.com.au", "44444444", "ED-APP-USERS", "321-098765-4", 100000.0, true);
        
        // prepare list
        ArrayList<Employee> empList = new ArrayList<>();
        empList.add(emp001);
        empList.add(emp002);
        empList.add(emp003);
        empList.add(emp004);
        
        // add data to db
        db.addRecords(empList);
    }
}