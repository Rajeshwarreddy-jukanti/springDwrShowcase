/**
 * Created by Dhafir Moussa on 22/10/2012.
 */
 
/**
 * Define th emain Angular app
 */
var ngApp = angular.module("ngApp",[]);

/**
 * Define the main dwrview controller
 */
ngApp.controller("ngController",

     /**
		 * Defines an AngularJs controller to handle request to and from
		 * EmployeeController
		 */
     function ngController($scope) {

		/**
		 * Initial values for employeeList and departmentList		 
		 */
		$scope.employeeList	= {};
		$scope.departmentList = {};
		$scope.employee = {};
		
		/**
		 * Fire a request to get all employees.
		 * 
		 */
		$scope.getAllEmployees = function(){
			
			// Here we are accessing the DwrEmployeeController directly via it's remote name "EmployeeController"
			// Notice that we are accessing the service methods by name not be request mapping.
			EmployeeController.getAllEmployees( function(response){
	     		
				//  Handle the response from the EmployeeController. Check if the
				//  process wasa successful then update the employee list				 
	     		if(response.success){
	     			$scope.employeeList = response.data;
	     			// since this callback is happening outside the Angular digest
					// cycle, we invok eteh $digest manually
	     			// to force view refresh
	     			$scope.$digest();
	     		}else{
	     			// delegate to the error handler
	     			$scope.handleServiceError(response);
	     		}
     		
			});			
		}
		
		/**
		 * Fire a request to get all departments.
		 * 
		 */
		$scope.getAllDepartments = function(){
			// Here we are accessing the DwrEmployeeController directly via it's remote name "EmployeeController"
			// Notice that we are accessing the service methods by name not be request mapping.
			EmployeeController.getAllDepartments( function(response){
	     		
				// Handle the response from the EmployeeController. Check if the
				// process wasa successful then update the employee list				 
	     		if(response.success){
	     			$scope.departmentList = response.data;
	     			// since this callback is happening outside the Angular digest
					// cycle, we invok eteh $digest manually
	     			// to force view refresh
	     			$scope.$digest();
	     		}else{
	     			// delegate to the error handler
	     			$scope.handleServiceError(response);
	     		}
     		
			});			
		}
		
		/**
		 * Retrieve the latest version of an employee given it's Id.
		 * 
		 * Although we a have a copy of the employee object in teh $scope model, it is safer
		 * to get the latest copy from the database in case it was updated/deleted by another transaction.
		 */
		$scope.editEmployee = function(employeeId){
			// Here we are accessing the DwrEmployeeController directly via it's remote name "EmployeeController"
			// Notice that we are accessing the service methods by name not be request mapping.
			EmployeeController.getEmployee(employeeId, function(response){
	     		
				// Handle the response from the EmployeeController. Check if the
				// process wasa successful then update the employee list				 
	     		if(response.success){
	     			$scope.employee = response.data;	     			
	     			// since this callback is happening outside the Angular digest
					// cycle, we invok eteh $digest manually
	     			// to force view refresh
	     			$scope.$digest();
	     		}else{
	     			// delegate to the error handler
	     			$scope.handleServiceError(response);
	     		}
     		
			});			
			
		}

		/**
		 * Retrieve the latest version of an employee given it's Id.
		 * 
		 * Although we a have a copy of the employee object in teh $scope model, it is safer
		 * to get the latest copy from the database in case it was updated/deleted by another transaction.
		 */
		$scope.saveEmployee = function(){			
			
			// Here we are accessing the DwrEmployeeController directly via it's remote name "EmployeeController"
			// Notice that we are accessing the service methods by name not be request mapping.
			EmployeeController.saveEmployee($scope.employee, function(response){
	     		
				// Handle the response from the EmployeeController. Check if the
				// process wasa successful then update the employee list				 
	     		if(response.success){
	     			$scope.employee = response.data;

	     			// We could just update employeeList in our model with the newly saved employee object,
	     			// or we could just refresh the enire list of employee
	     			$scope.getAllEmployees();
	     		}else{
	     			// delegate to the error handler
	     			$scope.handleServiceError(response);
	     		}
     		
			});			
			
		}

		/**
		 * Handles server errors. Show an alert with a response status and service message.
		 */
		$scope.handleServiceError = function(response){			
			alert(response.status+": "+response.message);
		}
		
		/**
		 * Clears the editor by initializing the employee model.
		 */
		$scope.clearEditor = function(){
			$scope.employee = {};
		}
		
		// get departments and Employee list
		$scope.getAllDepartments();
		$scope.getAllEmployees();
    }
);