<%@ page import="vo.EmployeeDetailsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read - Employees</title>
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
					<h1>Read</h1>
					<%
						EmployeeDetailsVO employee = (EmployeeDetailsVO) request.getAttribute("employee");
					%>
					<ul>
						<li>Employee ID : <%=employee.getEmployeeId()%>
						<li>First Name : <%=employee.getFirstName()%>
						<li>Last Name : <%=employee.getLastName()%>
						<li>Email : <%=employee.getEmail()%>
						<li>Phone Number : <%=employee.getPhoneNumber()%>
						<li>Hire Date : <%=employee.getHireDate()%>
						<li>Job Name (Job ID) : <%=employee.getJobTitle()%> (<%=employee.getJobId()%>)
						<li>Salary : <%=employee.getSalary()%>
						<li>Commission Percent : <%=employee.getCommissionPct()%>
						<li>Manager Name (Manager ID) : <%=employee.getManagerFirstName()%> <%=employee.getManagerLastName()%> (<%=employee.getManagerId()%>)
						<li>Department Name (Department ID) : <%=employee.getDepartmentName()%> (<%=employee.getDepartmentId()%>)
					</ul>
				</div>
			</div>
		</div>
	</div>

	<footer><h5>&copy; 2018 Ye-Hwan Kim</h5></footer>

</body>
</html>