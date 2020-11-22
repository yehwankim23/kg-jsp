package c06s04;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExceptionServlet
 */
@WebServlet("/Exception")
public class ExceptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExceptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rand = (int)(Math.random()*10);
		int a = 10;

		response.setContentType("text/html;charset=utf-8");

		try {
			int result = a / rand;

			request.setAttribute("result", result);

			RequestDispatcher disp = request.getRequestDispatcher("/c06s04/ErrorRuntime.jsp");
			disp.forward(request, response);

		} catch(RuntimeException e) {
			throw new ServletException(e.getMessage());

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
