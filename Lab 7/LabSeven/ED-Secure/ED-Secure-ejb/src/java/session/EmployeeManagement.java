/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Employee;
import entity.EmployeeDTO;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author elau
 */
@DeclareRoles({"ED-APP-ADMIN"})
@Stateless
public class EmployeeManagement implements EmployeeManagementRemote {

    @EJB
    private EmployeeFacadeLocal employeeFacade;

    private Employee employeeDTO2Entity(EmployeeDTO empDTO) {
        if (empDTO == null) {
            // just in case
            return null;
        }

        String empid = empDTO.getEmpid();
        String name = empDTO.getName();
        String phone = empDTO.getPhone();
        String address = empDTO.getAddress();
        String email = empDTO.getEmail();
        String password = empDTO.getPassword();
        String appGroup = empDTO.getAppGroup();
        String bankAccountId = empDTO.getBnkAccId();
        Double salary = empDTO.getSalary();
        Boolean active = empDTO.isActive();

        Employee employee = new Employee(empid, name, phone, address, email,
        password, appGroup, bankAccountId, salary, active);

        System.out.println("IN EMPLOYEE MANAGEMENT");
        return employee;
    }

    private EmployeeDTO employeeEntity2DTO(Employee employee) {
        if (employee == null) {
            // just in case
            return null;
        }

        EmployeeDTO empDTO = new EmployeeDTO(
        employee.getEmpid(),
        employee.getName(),
        employee.getPhone(),
        employee.getAddress(),
        employee.getEmail(),
        employee.getPassword(),
        employee.getAppGroup(),
        employee.getBnkAccId(),
        employee.getSalary(),
        employee.isActive()
        );

        return empDTO;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     * check whether the employee is in the system
     *
     * @param empId
     * @return true if the employee is in the system, false otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN", "ED-APP-USERS"})
    public boolean hasEmployee(String empId) {
        return employeeFacade.hasEmployee(empId);
    }

    /**
     * add an employee to the system
     *
     * @param empDTO
     * @return true if addition is successful, false otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public boolean addEmployee(EmployeeDTO empDTO) {

        if (empDTO == null) {
            // just in case
            return false;
        }

        // check employee exist?
        if (hasEmployee(empDTO.getEmpid())) {
            // employee exists, cannot add one
            return false;
        }

        // employee not exist
        // convert to entity
        Employee emp = this.employeeDTO2Entity(empDTO);
        // add one
        return employeeFacade.addEmployee(emp);
    }

    /**
     * update employee details without updating password
     *
     * @param empDTO
     * @return true if update is successful, false otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN", "ED-APP-USERS"})
    public boolean updateEmpolyeeDetails(EmployeeDTO empDTO) {
        // check employee exist?
        if (!hasEmployee(empDTO.getEmpid())) {
            return false;
        }

        // employee exist, update details
        // convert to entity class
        Employee employee = this.employeeDTO2Entity(empDTO);
        // update details
        return employeeFacade.updateEmployeeDetails(employee);
    }

    /**
     * update employee's password
     *
     * @param empId
     * @param newPassword
     * @return true if update successful, false otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN", "ED-APP-USERS"})
    public boolean updateEmployeePassword(String empId, String newPassword) {
        return employeeFacade.updatePassword(empId, newPassword);
    }

    /**
     * get employee details and use a DTO to transmit the details
     *
     * @param empId
     * @return a DTO containing the information of the employee if exists, null
     * otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN", "ED-APP-USERS"})
    public EmployeeDTO getEmployeeDetails(String empId) {
        // get the employee
        Employee employee = employeeFacade.find(empId);

        System.out.println("VIEWING EMPLOYEE DETAILS");

        if (employee == null) {
            // not found - no such employee
            return null;
        } else {
            // found - prepare details
            EmployeeDTO empDTO = new EmployeeDTO(employee.getEmpid(),
            employee.getName(), employee.getPhone(), employee.getAddress(),
            employee.getEmail(), employee.getPassword(), employee.getAppGroup(),
            employee.getBnkAccId(), employee.getSalary(), employee.isActive());

            return empDTO;
        }
    }

    /**
     * set the employee's active status to false
     *
     * @param empId
     * @return true if it can be set to inactive and have set to inactive; false
     * otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public boolean deleteEmployee(String empId) {
        return employeeFacade.deleteEmployee(empId);
    }

    /**
     * physically remove an employee's record from database
     *
     * This is for lab purposes - never ever do this in real world applications
     *
     * @param empId
     * @return true if the employee record has been physically removed from the
     * database, false otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public boolean removeEmployee(String empId) {
        return employeeFacade.removeEmployee(empId);
    }

}