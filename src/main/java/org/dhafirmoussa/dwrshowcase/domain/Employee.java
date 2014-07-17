package org.dhafirmoussa.dwrshowcase.domain;

import java.io.Serializable;

import org.dhafirmoussa.dwrshowcase.constants.Department;
import org.directwebremoting.annotations.DataTransferObject;

/**
 * A simple POJO representing an Employee
 */
@DataTransferObject
public class Employee implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Unique EMployee Id (normally automatically generated)
     */
    private Integer id;

    private String firstName;

    private String lastName;

    private Department department;

    /**
     * Get the id of the Employee object.
     * 
     * @return Returns the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the id member on the Employee object.
     * 
     * @param id
     *            The id to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the firstName of the Employee object.
     * 
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the firstName member on the Employee object.
     * 
     * @param firstName
     *            The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the lastName of the Employee object.
     * 
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the lastName member on the Employee object.
     * 
     * @param lastName
     *            The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the department of the Employee object.
     * 
     * @return Returns the department.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Set the department member on the Employee object.
     * 
     * @param department
     *            The department to set.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Validates this object.
     * 
     * @return true if valid, false otherwise.
     */
    public boolean isValid() {
        return getFirstName() != null && getFirstName().length() > 0 && getLastName() != null
                && getLastName().length() > 0 && getDepartment() != null;
    }

}
