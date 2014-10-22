package org.dhafirmoussa.dwrshowcase.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dhafirmoussa.dwrshowcase.constants.Department;
import org.dhafirmoussa.dwrshowcase.constants.Status;
import org.dhafirmoussa.dwrshowcase.domain.Employee;
import org.dhafirmoussa.dwrshowcase.service.EmployeeService;
import org.dhafirmoussa.dwrshowcase.transferobject.Response;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

/**
 * Handles DWR remote calls.
 */

@RemoteProxy(name = "EmployeeController")
public class DwrEmployeeController {

	protected static Logger logger = Logger
			.getLogger(DwrEmployeeController.class);

	/**
	 * EmployeeService will be injected as a resource called employeeService
	 */
	@Resource(name = "employeeService")
	private EmployeeService employeeService;

	/**
	 * Retrieves all employees as a {@link List} and then wrap it in a
	 * {@link Response} envelop.
	 * 
	 * @return a {@link Response} containing all employees as its payload data.
	 */
	@RemoteMethod
	public Response<List<Employee>> getAllEmployees() {
		DwrEmployeeController.logger
				.debug("Retrieving all employees and returning them wrapped in a Response object");

		// Retrieve and return all employees as payload to the response object.
		return new Response<>(this.employeeService.getAll());

	}

	/**
	 * Retrieves all departments as a {@link List} and then wrap it in a
	 * {@link Response} envelop.
	 * 
	 * @return a {@link Response} containing all departments as its payload
	 *         data.
	 */
	@RemoteMethod
	public Response<List<Department>> getAllDepartments() {
		DwrEmployeeController.logger
				.debug("Retrieving all departments as a list and adding it to ModelAttribute");

		return new Response<>(this.employeeService.getDepartments());
	}

	/**
	 * Retrieve a specific employee record. The method takes an employee Id to
	 * be used to retrieve the specific Employee from the repository.
	 * 
	 * @return the requested Employee object wrapped in a {@link Response} type.
	 */
	@RemoteMethod
	public Response<Employee> getEmployee(final Integer id) {
		DwrEmployeeController.logger
				.debug("Received request to get an employee object given a an employee Id");

		// Retrieve employee with matching id then return it as a payload of the
		// Response type.
		final Response<Employee> response = new Response<>();

		final Employee employee = this.employeeService.get(id);

		if (employee != null) {
			// found employee. We just set it as the payload
			response.setData(employee);
		} else {
			// employee not found. Set the response status as error and provide
			// an error message
			response.setStatus(Status.ERROR);
			response.setMessage("No employee was found with Id =" + id);
		}

		// return the expected response.
		// Note that this response could hold the requested employee if it was
		// successfully found, or an error status and message to say why.
		return response;

	}

	/**
	 * Saves the edited employee and display all employees again
	 * 
	 * @return the saved Employee object wrapped in a {@link Response} type.
	 */
	@RemoteMethod
	public Response<Employee> saveEmployee(final Employee employee) {
		DwrEmployeeController.logger
				.debug("Received request to update employee");

		final Response<Employee> response = new Response<>();

		// validate the object can be saved
		if (employee.isValid()) {

			// Delegate to EmployeeService for saving.
			response.setData(this.employeeService.save(employee));
		} else {
			// invalid employee to save. Set the response status and give a
			// proper message.
			response.setStatus(Status.ERROR);
			response.setMessage("Invalid employee properties.");
		}

		return response;
	}
}
