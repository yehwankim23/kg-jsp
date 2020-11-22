<%@ page import="java.util.List"%>
<%@ page import="vo.EmployeesVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List - Employees</title>
</head>
<body>

	<header><h1>Employees</h1></header>

	<nav>
		<h3>
			<a href="/Employees/home.jsp">Home</a>
			<a href="/Employees/create.jsp">Create</a>
			<a href="/Employees/list.jsp">List</a>
		</h3>
	</nav>

	<div id="page-body">
		<div class="containter">
			<div class="row wow fdeInUp">
				<div class="col-md-12-page-block">
					<h1>List</h1>
					<h3>
						<a href="employees.com?action=insert">Create</a>
					</h3>
					<table class="table table-striped table-bordered">
						<tr>
							<th>Employee ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Phone Number</th>
							<th>Hire Date</th>
							<th>Job ID</th>
							<th>Salary</th>
							<th>Commission Percent</th>
							<th>Manager ID</th>
							<th>Department ID</th>
						</tr>
						<%
							List<EmployeesVO> employeeList = (List<EmployeesVO>) request.getAttribute("employeeList");
							for (EmployeesVO employee : employeeList) {
						%>
						<tr>
							<td><a
								href="employees.com?action=read&employeeId=<%=employee.getEmployeeId()%>"><%=employee.getEmployeeId()%></a></td>
							<td><%=employee.getFirstName()%></td>
							<td><%=employee.getLastName()%></td>
							<td><%=employee.getEmail()%></td>
							<td><%=employee.getPhoneNumber()%></td>
							<td><%=employee.getHireDate()%></td>
							<td><%=employee.getJobId()%></td>
							<td><%=employee.getSalary()%></td>
							<td><%=employee.getCommissionPct()%></td>
							<td><%=employee.getManagerId()%></td>
							<td><%=employee.getDepartmentId()%></td>
						</tr>
						<%
							}
						%>
					</table>
				</div>
			</div>
		</div>
	</div>

	<footer><h5>&copy; 2018 Ye-Hwan Kim</h5></footer>

</body>
</html>