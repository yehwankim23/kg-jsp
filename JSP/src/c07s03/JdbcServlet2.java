package c07s03;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class JdbcServlet
 */
@WebServlet("/Jdbc2.do")
public class JdbcServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JdbcServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");

		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;

		Employee emp = new Employee();

		try {
			con = getConnection();
			System.out.println(con);

			String empnoStr = request.getParameter("empno");
			int empno = 101;

			if(empnoStr != null) {
				empno = Integer.parseInt(empnoStr);

			}

			String sql = "SELECT" + 
					"    *" + 
					"FROM" + 
					"    employees" + 
					"WHERE" + 
					"    employee_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empno);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setSalary(rs.getInt("salary"));
				emp.setDepartmentId(rs.getInt("department_id"));
				emp.setManagerId(rs.getInt("manager_id"));
				request.setAttribute("emp", emp);

			} else {
				request.setAttribute("nodata", "No DATA");

			}

			RequestDispatcher disp = request.getRequestDispatcher("/c07s03/JdbcExample.jsp");
			disp.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private Connection getConnection() {
		DataSource ds = null;
		Connection con = null;

		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");

			try {
				con = ds.getConnection();

			} catch(SQLException e) {
				e.printStackTrace();

			}

		} catch(NamingException e) {
			e.printStackTrace();

		}

		return con;

	}


}
