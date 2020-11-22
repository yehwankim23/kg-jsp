package c04s02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BindServlet
 */
@WebServlet("/Bind")
public class BindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ServletConfig config;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BindServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = config.getServletContext();
		application.setAttribute("message", "Hello World");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<h1>" + application.getAttribute("message"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
