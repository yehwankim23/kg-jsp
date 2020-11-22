package lab.board.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.domain.BoardDAO;
import lab.web.domain.BoardVO;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/Board.do")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		dao = new BoardDAO();

	}

	public void destroy() {
		dao = null;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");
		String url = "/index.jsp?url=";

		if("write".equals(action)) {
			request.setAttribute("message", "새 글 입력");
			url = url + "/board/write.jsp";
			request.setAttribute("action", "write_do");

		} else if("list".equals(action)) {
			String pageStr = request.getParameter("page");
			int page = 1;

			if(pageStr != null) {
				page = Integer.parseInt(pageStr);

			}

			int maxno = 10;
			Collection<BoardVO> lists = dao.selectArticleList(page, maxno);
			request.setAttribute("lists", lists);
			url = url + "/board/list.jsp";

			int bbsCount = dao.selectTotalBbsCount();
			int totalPage = 0;

			if(bbsCount > 0) {
				totalPage = bbsCount / 10;

			}

			if(totalPage % 10 != 0 || totalPage == 0) {
				totalPage = totalPage + 1;

			}

			request.setAttribute("totalPageCount", totalPage);
			request.setAttribute("page", page);

		} else if("view".equals(action)) {
			String bbsnoStr = request.getParameter("bbsno");
			int bbsno = 1;

			if(bbsnoStr != null) {
				bbsno = Integer.parseInt(bbsnoStr);

			}

			new BoardDAO().updateReadCount(bbsno);
			BoardVO board = new BoardDAO().selectArticle(bbsno);

			if(board.getContent() != null) {
				board.setContent(board.getContent().replaceAll("\n", "<br>"));

			}

			request.setAttribute("board", board);
			request.setAttribute("message", "글 상세보기");

			url = url + "/board/view.jsp";

		} else if("reply".equals(action)) {
			String bbsno = request.getParameter("bbsno");
			BoardVO board = dao.selectArticle(Integer.parseInt(bbsno));

			board.setSubject("[re]" + board.getSubject());
			board.setContent(board.getContent() + "\n---------\n");

			request.setAttribute("board", board);
			request.setAttribute("message", "댓글 입력");
			request.setAttribute("action", "reply_do");

			url = url + "/board/write.jsp";

		} else if("update".equals(action)) {
			String bbsnoStr = request.getParameter("bbsno");
			int bbsno = 0;

			if(bbsnoStr != null) {
				bbsno = Integer.parseInt(bbsnoStr);

				dao.updateReadCount(bbsno);
				BoardVO board = dao.selectArticle(bbsno);

				request.setAttribute("board", board);
				request.setAttribute("action", "update_do");

				url = url + "/board/write.jsp";

			} else {
				request.setAttribute("message", "게시글 번호가 없습니다.");
				url = url + "/board/write.jsp";

			}

		} else if("delete".equals(action)) {
			String bbsnoStr = request.getParameter("bbsno");
			String replynoStr = request.getParameter("replynumber");

			if(bbsnoStr != null) {
				request.setAttribute("bbsno", bbsnoStr);
				request.setAttribute("replynumber", replynoStr);
				request.setAttribute("action", "delete_do");

				url = url + "/board/delete.jsp";

			} else {
				request.setAttribute("message", "게시글 번호가 없습니다.");
				url = url + "/error/error.jsp";

			}

		} else {
			request.setAttribute("message", "잘못된 명령 : doGet-" + action	);
			url = url + "/error/error.jsp";

		}

		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");
		String url = "/index.jsp?url=";

		if("write_do".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");

			BoardVO board = new BoardVO();
			board.setName(name);
			board.setEmail(email);
			board.setPassword(password);
			board.setSubject(subject);
			board.setContent(content);

			dao.insertArticle(board);

			url = "/Board.do?action=list";
			response.sendRedirect(url);
			return;

		} else if("reply_do".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String password = request.getParameter("password");
			int bbsno = Integer.parseInt(request.getParameter("bbsno"));
			int masterid = Integer.parseInt(request.getParameter("masterid"));
			int replynumber = Integer.parseInt(request.getParameter("replynumber"));
			int replystep = Integer.parseInt(request.getParameter("replystep"));

			BoardVO board = new BoardVO();

			board.setBbsno(bbsno);
			board.setName(name);
			board.setEmail(email);
			board.setSubject(subject);
			board.setContent(content);
			board.setPassword(password);
			board.setMasterid(masterid);
			board.setReplynumber(replynumber);
			board.setReplystep(replystep);

			dao.replyArticle(board);

			response.sendRedirect("/Board.do?action=list");
			return;

		} else if("update_do".equals(action)) {
			String password = request.getParameter("password");
			String bbsnoStr = request.getParameter("bbsno");
			int bbsno = Integer.parseInt(bbsnoStr);
			String dbpw = dao.getPassword(bbsno);

			if(dbpw.equals(password)) {
				BoardVO board = new BoardVO();

				board.setBbsno(bbsno);
				board.setName(request.getParameter("name"));
				board.setEmail(request.getParameter("email"));
				board.setSubject(request.getParameter("subject"));
				board.setContent(request.getParameter("content"));

				dao.updateArticle(board);

				url = "/Board.do?action=view&bbsno=" + bbsno;

				response.sendRedirect(url);
				return;

			} else {
				request.setAttribute("message", "비밀번호가 다릅니다. 수정되지 않았습니다.");
				url = url + "/error/error.jsp";

			}

		} else if("delete_do".equals(action)) {
			String password = request.getParameter("password");
			int bbsno = Integer.parseInt(request.getParameter("bbsno"));
			int replynumber = Integer.parseInt(request.getParameter("replynumber"));
			String dbpw = dao.getPassword(bbsno);

			if(dbpw.equals(password)) {
				dao.deleteArticle(bbsno, replynumber);

				url = "Board.do?action=list";

				response.sendRedirect(url);
				return;

			} else {
				request.setAttribute("message", "잘못된 명령: doPost-" + action);
				url = url + "/error/error.jsp";

			}

		}

		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);

	}

}
