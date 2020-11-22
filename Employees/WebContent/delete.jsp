<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete - Employees</title>
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
					<h1>Delete</h1>
					<h3>Delete <%=request.getAttribute("name")%>'s information?</h3>
					<p>Verify email to delete.</p>
					<form action="employee.com" method="post">
						Email : <input type="email" name="email">
						<input type="hidden" name="employeeId" value="<%=request.getParameter("employeeId")%>">
						<input type="hidden" name="action" value="delete">
						<input type="submit" value="Submit">
						<input type="reset" value="Reset">
					</form>
				</div>
			</div>
		</div>
	</div>

	<footer><h5>&copy; 2018 Ye-Hwan Kim</h5></footer>

</body>
</html>