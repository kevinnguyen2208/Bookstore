/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edsecure;

import entity.EmployeeDTO;
import java.util.Scanner;
import javax.ejb.EJB;
import session.EmployeeManagementRemote;

/**
 *
 * @author elau
 */
public class AppClient {

    @EJB
    private static EmployeeManagementRemote employeeManagement;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AppClient client = new AppClient();

        EmployeeDTO empDTO1 = new EmployeeDTO("00099", "Edmonds",
                "9214436789", "99 John Street, Hawthorn", "edmonds@secure.com.au",
                "password", "ED-APP-USERS",
                "123-456789-0", 12345.0, true);

        client.addEMSEmployee(empDTO1);
        
        System.out.println("Want to remove the employee record just added? (Y/N)");
        Scanner sc = new Scanner(System.in);
        char answer = sc.next().trim().toUpperCase().charAt(0);
        if (answer == 'Y') {
            client.removeEMSEmployee(empDTO1);
        } else {
            // do nothing
        }
    }
    
    public void addEMSEmployee(EmployeeDTO empDTO) {
        System.out.println("Adding an employee to the database: " + empDTO.getName());
        boolean result = employeeManagement.addEmployee(empDTO);
        if (result) {
            System.out.println("The operation is successful.");
        } else {
            System.out.println("The operation fails!");
        }
    }
    
    public void removeEMSEmployee(EmployeeDTO empDTO) {
        System.out.println("Removing an employee from the database: " + empDTO.getName());
        
        boolean result = employeeManagement.removeEmployee(empDTO.getEmpid());
        
        if (result) {
            System.out.println("The remove operation is successful.");
        } else {
            System.out.println("The remove operation fails!");
        }
    }
    
}