<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Employees List using Spring MVC Style</title>

<style>
th {
	text-align: left
}
</style>
</head>
<body>
    <a href="/employee/spring/main/dwrview" > Go to DWR Style</a>
	<h1>Employees (Spring MVC Style)</h1>
	
		<table>
			<tr>
				<th width="50">Id</th>
				<th width="150">First Name</th>
				<th width="150">Last Name</th>
				<th width="100">Department</th>
				<th width="100">Action</th>

			</tr>
			<c:forEach items="${employeeList}" var="employee">
				<tr>
					<td><c:out value="${employee.id}" /></td>
					<td><c:out value="${employee.firstName}" /></td>
					<td><c:out value="${employee.lastName}" /></td>
					<td><c:out value="${employee.department}" /></td>
					<td><c:url var="editUrl"
							value="/spring/main/edit/${employee.id}" /> <a href="${editUrl}">Edit</a>
					</td>
				</tr>
			</c:forEach>


		</table>
	

	<script type="text/javascript">
		function test() {

			EmployeeController.getEmployee(1, function(response) {
				alert(response.success);
			})
		}
	</script>
</body>
</html>