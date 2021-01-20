/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.io.Serializable;

/**
 *
 * @author elau
 */
public class Employee implements Serializable {
    
    private final String empid;
    private final String name;
    private final String phone;
    private final String address;
    private final String email;
    private final String password;
    private final String appGroup;
    private final String bankAccountId;
    private final double salary;
    private final boolean active; 

    public Employee(String empid, String name, String phone, String address, 
            String email, String password, String appGroup,
            String bankAccountId, double salary, boolean active) {
        this.empid = empid;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.appGroup = appGroup;
        this.bankAccountId = bankAccountId;
        this.salary = salary;
        this.active = active;       // whether the employee is currently employed
    }

    public String getEmpid() {
        return empid;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getAppGroup() {
        return appGroup;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isActive() {
        return active;
    }

}
