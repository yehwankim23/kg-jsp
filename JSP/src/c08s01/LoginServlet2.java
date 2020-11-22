package c08s01;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import c07s03.MemberDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login2.do")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if(action != null && action.equals("logout")) {
			session.invalidate();
			RequestDispatcher disp = request.getRequestDispatcher("/c06s01/Loginform.jsp");
			disp.forward(request, response);

		} else {
			request.setAttribute("message", "잘못된 명령입니다.");
			RequestDispatcher disp = request.getRequestDispatcher("/c06s04/Error.jsp");
			disp.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		MemberDAO dao = new MemberDAO();

		HttpSession session = request.getSession();
		String url = "/c06s04/Error.jsp";

		try {
			String dbpw = dao.getPassword(userid);

			if(dbpw.equals(password)) {
				session.setAttribute("userid", userid);
				request.setAttribute("message", userid + "님 환영합니다.");
				url = "/c06s04/Ok.jsp";

			} else {
				session.invalidate();
				request.setAttribute("message", "비밀번호가 다릅니다");
				url ="/c06s04/Error.jsp";

			}

		} catch(RuntimeException e) {
			session.invalidate();
			request.setAttribute("message", e.getMessage());
			url="/c06s04/Error.jsp";

		}

		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);

	}

}
