package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.DepartmentsVO;
import vo.EmployeeDetailsVO;
import vo.EmployeesVO;
import vo.JobsVO;

public class EmployeesDAO {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 로드할 수 없습니다.");

		}

	}

	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String ID = "hr";
	private static final String PW = "hr";

	public List<EmployeesVO> selectAllEmployees() {
		String sql = "SELECT * "
				+ "FROM employees";
		List<EmployeesVO> employeeList = new ArrayList<>();
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				EmployeesVO employee = new EmployeesVO();

				employee.setEmployeeId(rs.getInt("employee_id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setEmail(rs.getString("email"));
				employee.setPhoneNumber(rs.getString("phone_number"));
				employee.setHireDate(rs.getDate("hire_date"));
				employee.setJobId(rs.getString("job_id"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setCommissionPct(rs.getDouble("commission_pct"));
				employee.setManagerId(rs.getInt("manager_id"));
				employee.setDepartmentId(rs.getInt("department_id"));
				employeeList.add(employee);

			}

			return employeeList;

		} catch(SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if(con != null) {
				try {
					con.close();

				} catch(SQLException e) {
					//

				}

			}

		}

	}

	public EmployeesVO selectEmployeeDetailsByEmployeeId(int employeeId) {
		String sql = "SELECT e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.job_id, j.job_title, e.salary, e.commission_pct, e.manager_id, m.first_name AS manager_first_name, m.last_name AS manager_last_name, e.department_id, d.department_name "
				+ "FROM employees e "
				+ "LEFT JOIN departments d ON e.department_id = d.department_id JOIN jobs j ON e.job_id = j.job_id LEFT JOIN employees m ON e.manager_id = m.employee_id "
				+ "WHERE e.employee_id = ?";
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			EmployeeDetailsVO employeeDetails = new EmployeeDetailsVO();

			if(rs.next()) {
				employeeDetails.setEmployeeId(rs.getInt("employee_id"));
				employeeDetails.setFirstName(rs.getString("first_name"));
				employeeDetails.setLastName(rs.getString("last_name"));
				employeeDetails.setEmail(rs.getString("email"));
				employeeDetails.setPhoneNumber(rs.getString("phone_number"));
				employeeDetails.setHireDate(rs.getDate("hire_date"));
				employeeDetails.setJobId(rs.getString("job_id"));
				employeeDetails.setJobTitle(rs.getString("job_title"));
				employeeDetails.setSalary(rs.getDouble("salary"));
				employeeDetails.setCommissionPct(rs.getDouble("commission_pct"));
				employeeDetails.setManagerId(rs.getInt("manager_id"));
				employeeDetails.setManagerFirstName(rs.getString("manager_first_name"));
				employeeDetails.setManagerLastName(rs.getString("manager_last_name"));
				employeeDetails.setDepartmentId(rs.getInt("department_id"));
				employeeDetails.setDepartmentName(rs.getString("department_name"));

			}

			return employeeDetails;

		} catch(SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if(con != null) {
				try {
					con.close();

				} catch(SQLException e) {
					//

				}

			}

		}

	}

	public List<JobsVO> selectAllJobs() {
		String sql = "SELECT job_id, job_title "
				+ "FROM jobs";
		List<JobsVO> jobList = new ArrayList<>();
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				JobsVO job = new JobsVO();

				job.setJobId(rs.getString("job_id"));
				job.setJobTitle(rs.getString("job_title"));
				jobList.add(job);

			}

			return jobList;

		} catch(SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if(con != null) {
				try {
					con.close();

				} catch(SQLException e) {
					//

				}

			}

		}

	}

	public List<DepartmentsVO> selectAllDepartments() {
		String sql = "SELECT department_id, department_name "
				+ "FROM departments";
		List<DepartmentsVO> departmentList = new ArrayList<>();
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				DepartmentsVO department = new DepartmentsVO();

				department.setDepartmentId(rs.getInt("department_id"));
				department.setDepartmentName(rs.getString("department_name"));
				departmentList.add(department);

			}

			return departmentList;

		} catch(SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if(con != null) {
				try {
					con.close();

				} catch(SQLException e) {
					//

				}

			}

		}

	}

	public List<EmployeesVO> selectAllManagers() {
		String sql = "SELECT DISTINCT e.employee_id, e.first_name, e.last_name "
				+ "FROM employees e "
				+ "RIGHT JOIN employees m ON m.manager_id = e.employee_id "
				+ "ORDER BY e.employee_id";
		List<EmployeesVO> managerList = new ArrayList<>();
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				EmployeesVO manager = new EmployeesVO();

				manager.setEmployeeId(rs.getInt("employee_id"));
				manager.setFirstName(rs.getString("first_name"));
				manager.setLastName(rs.getString("last_name"));
				managerList.add(manager);

			}

			return managerList;

		} catch(SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if(con != null) {
				try {
					con.close();

				} catch(SQLException e) {
					//

				}

			}

		}

	}

	public int insertEmployee(EmployeesVO employee) {
		String sql = "INSERT INTO employees ( employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id ) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, employee.getEmployeeId());
			pstmt.setString(2, employee.getFirstName());
			pstmt.setString(3, employee.getLastName());
			pstmt.setString(4, employee.getEmail());
			pstmt.setString(5, employee.getPhoneNumber());
			pstmt.setDate(6, employee.getHireDate());
			pstmt.setString(7, employee.getJobId());
			pstmt.setDouble(8, employee.getSalary());
			pstmt.setDouble(9, employee.getCommissionPct());
			pstmt.setInt(10, employee.getManagerId());
			pstmt.setInt(11, employee.getDepartmentId());

			int rowCount = pstmt.executeUpdate();

			if(rowCount > 0) {
				return rowCount;

			} else {
				throw new RuntimeException("No row inserted.");

			}

		} catch(SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if(con != null) {
				try {
					con.close();

				} catch(SQLException e) {
					//

				}

			}

		}

	}

	public EmployeesVO selectEmployeeByEmployeeId(int employeeId) {
		String sql = "SELECT * "
				+ "FROM employees "
				+ "WHERE employee_id = ?";
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			EmployeesVO employee = new EmployeesVO();

			if(rs.next()) {
				employee.setEmployeeId(rs.getInt("employee_id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setEmail(rs.getString("email"));
				employee.setPhoneNumber(rs.getString("phone_number"));
				employee.setHireDate(rs.getDate("hire_date"));
				employee.setJobId(rs.getString("job_id"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setCommissionPct(rs.getDouble("commission_pct"));
				employee.setManagerId(rs.getInt("manager_id"));
				employee.setDepartmentId(rs.getInt("department_id"));

			}

			return employee;

		} catch(SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if(con != null) {
				try {
					con.close();

				} catch(SQLException e) {
					//

				}

			}

		}

	}

	public int updateEmployee(EmployeesVO employee) {
		String sql = "UPDATE employees "
				+ "SET first_name = ?, last_name = ?, email = ?, phone_number = ?, hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, manager_id = ?, department_id = ? "
				+ "WHERE employee_id = ?";
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setString(3, employee.getEmail());
			pstmt.setString(4, employee.getPhoneNumber());
			pstmt.setDate(5, employee.getHireDate());
			pstmt.setString(6, employee.getJobId());
			pstmt.setDouble(7, employee.getSalary());
			pstmt.setDouble(8, employee.getCommissionPct());
			pstmt.setInt(9, employee.getManagerId());
			pstmt.setInt(10, employee.getDepartmentId());
			pstmt.setInt(11, employee.getEmployeeId());

			int rowCount = pstmt.executeUpdate();

			if(rowCount > 0) {
				return rowCount;

			} else {
				throw new RuntimeException("No row inserted.");

			}

		} catch(SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if(con != null) {
				try {
					con.close();

				} catch(SQLException e) {
					//

				}

			}

		}

	}

	public String selectNameByEmployeeId(int employeeId) {
		String sql = "SELECT fist_name, last_name "
				+ "FROM employees "
				+ "WHERE employee_id = ?";
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();

			String name = "";

			if(rs.next()) {
				name = rs.getString("first_name") + " " + rs.getString("last_name");

			}

			return name;

		} catch(SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if(con != null) {
				try {
					con.close();

				} catch(SQLException e) {
					//

				}

			}

		}

	}

	public int deleteEmployee(int employeeId, String email) {
		String sql = "DELETE employees "
				+ "WHERE employee_id = ? and email = ?";
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			pstmt.setString(2, email);

			int rowCount = pstmt.executeUpdate();

			if(rowCount > 0) {
				return rowCount;

			} else {
				throw new RuntimeException("No row inserted.");

			}

		} catch(SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if(con != null) {
				try {
					con.close();

				} catch(SQLException e) {
					//

				}

			}

		}

	}

}
