package org.dhafirmoussa.dwrshowcase.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dhafirmoussa.dwrshowcase.constants.Department;
import org.dhafirmoussa.dwrshowcase.domain.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Employee.
 */
@Service("employeeService")
@Transactional
public class EmployeeService {

    protected static Logger logger = Logger.getLogger("service");

    /**
     * Dummy database
     */
    private HashMap<Integer, Employee> database = new HashMap<>();


    /**
     * Default service constructor to mock an Employee service that has access to the DB or some other EIS.
     */
    public EmployeeService() {
        // - Initialize dummy employee database
        init();
    }

    /**
     * Retrieves all employees.
     * 
     * @return a list of employees
     */
    public List<Employee> getAll() {
        logger.debug("Retrieving all employees");

        // - Initialize our array
        List<Employee> employees = new ArrayList<>();

        // - Iterate the database
        for (Map.Entry<Integer, Employee> entry: database.entrySet()) {
            employees.add(entry.getValue());
        }

        // - Return all employees
        return employees;
    }

    /**
     * Retrieves a employee based on his id. Will return null if employee doesn't exist.
     * 
     * @param id
     *            the identifier of the employee
     * @return a employee
     */
    public Employee get(Integer id) {
        logger.debug("Retrieving employee based on his id");
        return database.get(id);
    }

    /**
     * Updates/add an employee. If the employee exists then it will be updated, if not then it will be added.
     */
    public Employee save(Employee employee) {
        logger.debug("updating or adding an employee");

        if (employee.getId() == null) {
            // this is a new employee object. We assign it a unique Id
            // grab the next maximum Id
            employee.setId(database.size() + 1);
        }

        // Note this is not the best way to update/add a data! I only did it for the sake of simplicity
        // set the emloyee
        database.put(employee.getId(), employee);

        // return the saved version of the employee (in case other fields were updated during the save operation)
        return employee;
    }

    /**
     * Returns a {@link List} of Each department
     * 
     * @return {@link List} of Departments
     */
    public List<Department> getDepartments() {
        return Arrays.asList(Department.values());

    }

    /**
     * Initialize a list of employees
     */
    private void init() {
        // - New employee
        Employee employee = new Employee();

        employee.setFirstName("John");
        employee.setLastName("Smith");
        employee.setDepartment(Department.FINANCE);

        // - Add to list
        save(employee);

        // - New employee
        employee = new Employee();

        employee.setFirstName("Joe");
        employee.setLastName("Bloggs");
        employee.setDepartment(Department.PARTS);

        // - Add to list
        save(employee);

        // - New employee
        employee = new Employee();

        employee.setFirstName("Mark");
        employee.setLastName("Twain");
        employee.setDepartment(Department.SERVICE);

        // - Add to list
        save(employee);

        // - New employee
        employee = new Employee();

        employee.setFirstName("Steven");
        employee.setLastName("Hopkins");
        employee.setDepartment(Department.RESOURCE);

        // - Add to list
        save(employee);

        // - New employee
        employee = new Employee();

        employee.setFirstName("Jane");
        employee.setLastName("Graham");
        employee.setDepartment(Department.TECHNICAL);

        // - Add to list
        save(employee);

    }
}
