package org.dhafirmoussa.dwrshowcase.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dhafirmoussa.dwrshowcase.constants.AttributeName;
import org.dhafirmoussa.dwrshowcase.constants.Department;
import org.dhafirmoussa.dwrshowcase.constants.WebView;
import org.dhafirmoussa.dwrshowcase.domain.Employee;
import org.dhafirmoussa.dwrshowcase.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves Employee related requests
 */
@Controller
@RequestMapping("/main")
public class SpringEmployeeController {

    protected static Logger logger = Logger.getLogger(SpringEmployeeController.class);

    /**
     * EmployeeService will be injected as a resource called employeeService
     */
    @Resource(name = "employeeService")
    private EmployeeService employeeService;


    /**
     * Retrieves all employees as a list and add it to the model. The name of the attribute will be "employees" Since
     * this method is annotated with @ModelAttribute it will be called <i>before</i> each request to this controller.
     * 
     * @return a {@link List} containing all employees.
     */
    @ModelAttribute(AttributeName.EMPLOYEE_LIST)
    public List<Employee> getAllemployees() {
        logger.debug("Retrieving all employees and adding it to ModelAttribute");

        // - Delegate to EmployeeService
        return employeeService.getAll();
    }

    /**
     * Retrieves all department types and add them to the model to be used by the JSP. Since this method is annotated
     * with @ModelAttribute it will be called <i>before</i> each request to this controller.
     * 
     * @return a {@link List} of department options.
     */
    @ModelAttribute(AttributeName.DEPARTMENT_LIST)
    public List<Department> getAllDepartments() {
        logger.debug("Retrieving all departments as a list and adding it to ModelAttribute");

        return employeeService.getDepartments();
    }

    /**
     * Handles and retrieves a JSP page containing all employees
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getAllPage() {
        logger.debug("Received request to show all employees page");

        // The employeespage.jsp uses a model attribute named "employees" to display all employees.
        // The attribute has been automatically added to the model earlier before this method was called.

        // This will resolve to /WEB-INF/jsp/employeespage.jsp
        return WebView.EMPLOYEES_VIEW;
    }

    /**
     * Handle a GET request to return the dwrEmployee page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(method = RequestMethod.GET, value = "/dwrview")
    public String getDwrView() {
        logger.debug("Received request to show DWR view page");


        // This will resolve to /WEB-INF/jsp/employeespage.jsp
        return WebView.DWR_VIEW;
    }

    /**
     * Retrieve the edit page to edit a specific employee record. The request contains the employee Id to be used to
     * retrive the specific Employee from the repository.
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable Integer id, Model model) {
        logger.debug("Received request to show edit page");

        // Retrieve employee with matching id then add this employee to the model
        // The employeeEditor.jsp references a model attribute named "employee" we are about to add to the model.
        //
        // Imortant! This "employee" will be referenced again once we send the updated form data.
        // It is important to keep the attribute names consistent so that Spring can match and find and bind the right
        // object.

        // find the requested employee and add it to the model under "employee" model name.
        // Note this model attribute name will be the same to use when annotating the parameter Employee in the
        // saveEmployee method below.
        model.addAttribute(AttributeName.EMPLOYEE, employeeService.get(id));

        // The employeeEditor.jsp references a model attribute named "departmentList".
        // This model attribute is passed automatically when getAllDepartments() method was called before this method
        // was invoked.

        // This will resolve to /WEB-INF/jsp/employeeEditor.jsp
        return WebView.EMPLOYEE_EDITOR;
    }

    /**
     * Saves the edited employee and display all employees again
     * 
     * @return JSP names of the view
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute(AttributeName.EMPLOYEE) Employee employee, @PathVariable Integer id,
            Model model) {
        logger.debug("Received request to update employee");

        // The "employee" model has been passed to the controller from the JSP containg all the editable details of the
        // employee object.

        // We manually assign the id because the Id field is disabled on the editor page and hence doesn't get
        // submitted.
        // When a field is disabled it will not be included in the ModelAttribute
        employee.setId(id);

        // Delegate to EmployeeService for saving.
        employeeService.save(employee);

        // We now show the all employees page again after updating the employee.
        // After doing a POST request we would like to fire a redirect request to our employeesView
        // page. This is to follow the PRG Pattern.
        // However, because of the ModelAttribute of the employeeList and departmentList been called just before
        // doing this update, they will appear in the request URL of the redirect url.
        // See
        //
        // http://stackoverflow.com/questions/2163517/how-do-i-prevent-spring-3-0-mvc-modelattribute-variables-from-appearing-in-url
        //
        // To avoid all that hassles, we have two options:
        // (1) just remove the attributes from the model since the browser will issue a GET
        // request again which will repopulate the model with up to date attributes.
        //
        // (2) Alternatively, we can update the model attribute with a fresh copy of EmployeeList and return
        // the employeesview jsp name.
        //
        // I prefer option (1) for our context.
        model.asMap().clear();

        return "redirect:/spring/main";
    }
}
