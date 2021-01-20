/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author elau
 */
public class EmployeeDTO implements Serializable {
    
    String empid;
    String name;
    String phone;
    String address;
    String email;
    String password;
    String appGroup;
    String bnkAccId;
    Double salary;
    Boolean active;
    
    public EmployeeDTO(String empid, String name, String phone, String address,
            String email, String password, String appGroup, 
            String bnkAccId, Double salary, Boolean active) {
        this.empid = empid;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.appGroup = appGroup;
        this.bnkAccId = bnkAccId;
        this.salary = salary;
        this.active = active;
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
    
    public String getBnkAccId() {
        return bnkAccId;
    }

    public Double getSalary() {
        return salary;
    }

    public Boolean isActive() {
        return active;
    }
    
}
