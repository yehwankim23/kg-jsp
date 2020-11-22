package c04s01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleServlet
 */
@WebServlet(
		urlPatterns = {"/LifeCycle.do"},
		initParams = {
				@WebInitParam(name="email", value="gctserf@gmail.com"),
				@WebInitParam(name="userid", value="eomyb")
		})

public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LifeCycleServlet() {
		super();
		System.out.println("서블릿 생성자 호출");

	}

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		System.out.println("init() 메서드 호출");

	}

	public void destroy() {
		System.out.println("destroy() 메서드 호출");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출됨");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String email = config.getInitParameter("email");
		String userid = config.getInitParameter("userid");

		out.println("사용자 이메일 : " + email);
		out.println("사용자 아이디 : " + userid);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
