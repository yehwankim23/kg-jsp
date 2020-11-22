package c02s02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BBSPostServlet2
 */
@WebServlet("/BBS2")
public class BBSPostServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BBSPostServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");

		if(gender.equals("Male")) {
			gender = "남";

		} else {
			gender = "여";

		}

		String iNotice = request.getParameter("inotice");
		String clNotice = request.getParameter("cnotice");
		String dNotice = request.getParameter("dnotice");
		String job = request.getParameter("job");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<HTML>");
		out.println("<HEAD><TITLE>개인 정보 입력 - 결과 화면</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("<H2>개인 정보 입력</H2>");
		out.printf("이름: %s <BR>", name);
		out.printf("아이디: %s <BR>", id);
		out.printf("암호: %s <BR>", password);
		out.printf("성별: %s <BR>", gender);
		out.printf("공지 메일: %s <BR>", noticeToHangul(iNotice));
		out.printf("광고 메일: %s <BR>", noticeToHangul(clNotice));
		out.printf("배송 확인 메일: %s <BR>", noticeToHangul(dNotice));
		out.printf("직업: %s <BR>", job);
		out.println("</BODY>");
		out.println("</HTML>");

	}

	private String noticeToHangul(String notice) {
		if (notice == null) {
			return "받지않음";

		} else if(notice.equals("on")){
			return "받음";

		} else {
			return notice;

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<HTML>");
		out.println("<HEAD><TITLE>게시판 글쓰기 - 결과 화면</TITLE></HEAD>");
		out.println("<BODY>");
		out.printf("이름: %s <BR>", name);
		out.printf("제목: %s <BR>", title);
		out.println("--------<BR>");
		out.printf("<PRE>%s</PRE>", content);
		out.println("--------<BR>");
		out.println("저장되었습니다.");
		out.println("</BODY>");
		out.println("</HTML>");

	}

}
