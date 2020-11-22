<%@ page import="java.util.List"%>
<%@ page import="vo.DepartmentsVO"%>
<%@ page import="vo.EmployeesVO"%>
<%@ page import="vo.JobsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create - Employees</title>
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
					<h1>Create</h1>
					<form action="/employees.com" method="post">
						<table>
							<tr>
								<td>Employee ID</td>
								<td><input type="number" name="employeeID"></td>
							</tr>
							<tr>
								<td>Name</td>
								<td><input type="text" name="firstName"><input
									type="text" name="lastName"></td>
							</tr>
							<tr>
								<td>Email</td>
								<td><input type="email" name="email"></td>
							</tr>
							<tr>
								<td>Phone Number</td>
								<td><input type="text" name="phoneNumber"></td>
							</tr>
							<tr>
								<td>Hire Date</td>
								<td><input type="date" name="hireDate"></td>
							</tr>
							<tr>
								<td>Job ID</td>
								<%
									List<JobsVO> jobList = (List<JobsVO>) request.getAttribute("jobList");
								%>
								<td><select class="form-control" name="jobId">
									<%
										for (JobsVO job : jobList) {
									%>
										<option value="<%=job.getJobId()%>"><%=job.getJobTitle()%>
									<%
										}
									%>
								</select></td>
							</tr>
							<tr>
								<td>Salary</td>
								<td><input type="number" name="salary"></td>
							</tr>
							<tr>
								<td>Commission Percent</td>
								<td><input type="number" name="commissionPct" min="0" max="1" step="0.01"></td>
							</tr>
							<tr>
								<td>Manager ID</td>
								<%
									List<EmployeesVO> managerList = (List<EmployeesVO>) request.getAttribute("managerList");
								%>
								<td><select class="form-control" name="managerId">
									<%
										for (EmployeesVO manager : managerList) {
									%>
										<option value="<%=manager.getEmployeeId()%>"><%=manager.getFirstName()%><%=manager.getLastName()%>
									<%
										}
									%>
								</select></td>
							</tr>
							<tr>
								<td>Department ID</td>
								<%
									List<DepartmentsVO> departmentList = (List<DepartmentsVO>) request.getAttribute("departmentList");
								%>
								<td><select class="form-control" name="departmentId">
									<%
										for (DepartmentsVO department : departmentList) {
									%>
										<option value="<%=department.getDepartmentId()%>"><%=department.getDepartmentName()%>
									<%
										}
									%>
								</select></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>

	<footer><h5>&copy; 2018 Ye-Hwan Kim</h5></footer>

</body>
</html>