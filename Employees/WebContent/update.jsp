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
<title>Update - Employees</title>
</head>
<body>

	<div id="page-body">
		<div class="containter">
			<div class="row wow fdeInUp">
				<div class="col-md-12-page-block">
					<h1>Create</h1>
					<form action="/employees.com" method="post">
						<%
							EmployeesVO employee = (EmployeesVO) request.getAttribute("employee");
						%>
						<table>
							<tr>
								<td>Employee ID</td>
								<td><input type="number" name="employeeID" value="<%=employee.getEmployeeId()%>" readonly></td>
							</tr>
							<tr>
								<td>Name</td>
								<td>
									<input type="text" name="firstName" value="<%=employee.getFirstName()%>">
									<input type="text" name="lastName" value="<%=employee.getLastName()%>">
								</td>
							</tr>
							<tr>
								<td>Email</td>
								<td><input type="email" name="email" value="<%=employee.getEmail()%>"></td>
							</tr>
							<tr>
								<td>Phone Number</td>
								<td><input type="text" name="phoneNumber" value="<%=employee.getPhoneNumber()%>"></td>
							</tr>
							<tr>
								<td>Hire Date</td>
								<td><input type="date" name="hireDate" value="<%=employee.getHireDate()%>"></td>
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
										<option value="<%=job.getJobId()%>"
											<%
												if (employee.getJobId().equals(job.getJobId())) {
											%>
												selected
											<%
												}
											%>
										> <%=job.getJobTitle()%>
									<%
										}
									%>
								</select></td>
							</tr>
							<tr>
								<td>Salary</td>
								<td><input type="number" name="salary" value="<%=employee.getSalary()%>"></td>
							</tr>
							<tr>
								<td>Commission Percent</td>
								<td><input type="number" name="commissionPct" min="0" max="1" step="0.01" value="<%=employee.getCommissionPct()%>"></td>
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
										<option value="<%=manager.getEmployeeId()%>"
											<%
												if (employee.getManagerId() == (manager.getEmployeeId())) {
											%>
												selected
											<%
												}
											%>
										><%=manager.getFirstName()%><%=manager.getLastName()%>
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
										<option value="<%=department.getDepartmentId()%>"
											<%
												if (employee.getDepartmentId() == (department.getDepartmentId())) {
											%>
												selected
											<%
												}
											%>
										><%=department.getDepartmentName()%>
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

</body>
</html>