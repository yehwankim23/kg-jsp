package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeesDAO;
import vo.DepartmentsVO;
import vo.EmployeesVO;
import vo.JobsVO;

/**
 * Servlet implementation class EmployeesServlet
 */
@WebServlet("/employees.com")
public class EmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	EmployeesDAO dao = new EmployeesDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = "/";

		if("home".equals(action)) {
			url = "home.jsp";

		} else if("list".equals(action)) {
			List<EmployeesVO> employeeList = dao.selectAllEmployees();

			request.setAttribute("employeeList", employeeList);

			url = "/list.jsp";

		} else if("create".equals(action)) {
			List<DepartmentsVO> departmentList = dao.selectAllDepartments();
			List<EmployeesVO> managerList = dao.selectAllManagers();
			List<JobsVO> jobList = dao.selectAllJobs();

			request.setAttribute("jobList", jobList);
			request.setAttribute("managerList", managerList);
			request.setAttribute("departmentList", departmentList);

			url = "/create.jsp";

		} else if("read".equals(action)) {
			String employeeIdStr = request.getParameter("employeeId");

			int employeeId = Integer.parseInt(employeeIdStr);
			EmployeesVO employee = dao.selectEmployeeDetailsByEmployeeId(employeeId);

			request.setAttribute("employee", employee);

			url = "/read.jsp";

		} else if("update".equals(action)) {
			request.setAttribute("jobList", dao.selectAllJobs());
			request.setAttribute("managerList", dao.selectAllManagers());
			request.setAttribute("departmentList", dao.selectAllDepartments());
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			request.setAttribute("employee", dao.selectEmployeeByEmployeeId(employeeId));

			url = "/update.jsp";

		} else if("delete".equals(action)) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String email = request.getParameter("email");

			try {
				dao.deleteEmployee(employeeId, email);

				url = "/employees.com?action=list";

			} catch(RuntimeException e) {
				e.printStackTrace();
				request.setAttribute("message", e.getMessage());

				url = "/error.jsp";

				RequestDispatcher disp = request.getRequestDispatcher(url);
				disp.forward(request, response);

				return;

			}
		} else {
			request.setAttribute("message", "요청한 명령이 없습니다.");

			url = "/error.jsp";

			RequestDispatcher disp = request.getRequestDispatcher(url);
			disp.forward(request, response);

			return;

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
