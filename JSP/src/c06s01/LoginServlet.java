package c06s01;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String action = request.getParameter("action");
		String id = "gctserf";
		String pw = "0000";
		String url = "/c06s01/Error.jsp";

		HttpSession session = request.getSession();

		if(("login").equals(action) && userid.equals(id) && password.equals(pw)) {
			session.setAttribute("userid", userid);
			request.setAttribute("message", userid+" 님 환영합니다.");
			url = "/c06s01/Ok.jsp";

		} else if(("login").equals(action) && password.equals(pw) == false) {
			request.setAttribute("message", "비밀번호가 다릅니다");
			url = "/c06s01/Error.jsp";

		} else if(("login").equals(action) && id.equals(id) == false) {
			request.setAttribute("message", "없는 아이디 입니다.");
			url = "/c06s01/Error.jsp";

		} else if(("logout").equals(action)) {
			session.invalidate();
			url = "/c06s01/LoginForm.jsp";

		} else if(null == action) {
			if(!id.equals(session.getAttribute("userid"))) {
				request.setAttribute("message", "세션 만료 시간이 다 되었습니다");
				url = "/c06s01/Error.jsp";

			}else {
				url = "/c06s01/LoginForm.jsp";

			}

		}

		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);

	}

}
