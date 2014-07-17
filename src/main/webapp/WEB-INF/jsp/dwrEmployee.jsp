<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type='text/javascript' src="/employee/dwr/engine.js"></script>
<script type='text/javascript' src="/employee/dwr/util.js"></script>
<script type="text/javascript"
	src="/employee/dwr/interface/EmployeeController.js"></script>

<a href="/employee/spring/main" > Go to Spring MVC Style</a>
<title>Employees List using Spring MVC Style</title>

<style>
th {
	text-align: left
}
</style>
</head>
<body>

	<script src="/employee/js/angular.js" type="text/javascript"></script>
	<h1>Employees (DWR Style)</h1>
	<div ng-app="ngApp" ng-cloak ng-controller="ngController">
		<table>
			<tr>
				<th width="50">Id</th>
				<th width="150">First Name</th>
				<th width="150">Last Name</th>
				<th width="100">Department</th>
				<th width="100">Action</th>
			</tr>

			<tr ng-repeat="employee in employeeList">
				<td>{{employee.id}}</td>
				<td>{{employee.firstName}}</td>
				<td>{{employee.lastName}}</td>
				<td>{{employee.department}}</td>
				<td><button ng-click="editEmployee(employee.id)">Edit</button>
				</td>
			</tr>
		</table>


		<!-- The employee editor -->
		<div id="employeeEditor">		  
			<hr />
			<h3>Employee Editor</h3>
			<table>
				<tr>
					<td>Id</td>
					<td>{{employee.id}}</td>
				</tr>

				<tr>
					<td>First Name:</td>
					<td><input ng-model="employee.firstName" type = "text"/></td>
				</tr>
                <tr>
                    <td>Last Name:</td>
                    <td><input ng-model="employee.lastName" type = "text"/></td>
                </tr>
                <tr>
                    <td>Department:</td>                    
					<td>
					    <select ng-model="employee.department" ng-options="department for department in departmentList"></select>  					
					</td>
				</tr>
			</table>
			<div>
			     <button ng-click="clearEditor()" >Clear</button>
			     <button ng-click="saveEmployee()" ng-disabled="invalidEmployee()">Save</button>
			</div>
		</div>
	</div>
	<script src="/employee/js/dwrEmployee.js" type="text/javascript"></script>
</body>