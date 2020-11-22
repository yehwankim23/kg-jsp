package c06s03;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RdExample
 */
@WebServlet("/Rd")
public class RdExample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RdExample() {
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
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String rd = request.getParameter("rd");

		String url = "/c06s03/RdExample.jsp";

		if(rd.equals("RE")) {
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("pw", pw);
			response.sendRedirect(url);

		} else {
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("pw", pw);

			RequestDispatcher disp = request.getRequestDispatcher(url);
			disp.forward(request, response);

		}

	}

}
