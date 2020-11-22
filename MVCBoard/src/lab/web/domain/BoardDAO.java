package lab.web.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private Connection getConnection() {
		DataSource ds = null;
		Connection con = null;

		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			con = ds.getConnection();

		} catch(Exception e) {
			e.printStackTrace();

		}

		return con;

	}

	private void closeConnection(Connection con) {
		if(con != null) {
			try {
				con.close();

			} catch(SQLException e) {
				//

			}

		}

	}

	public void insertArticle(BoardVO board) {
		Connection con = null;
		String sql1 = "SELECT nvl(MAX(bbsno),0) "
				+ "FROM board";
		int bbsno = 0;
		String sql2 = "INSERT INTO board ( bbsno, name, password, email, subject, content, writedate, masterid, readcount, replynumber, replystep ) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, SYSDATE, ?, 0, 0, 0 )";

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			bbsno = rs.getInt(1) + 1;

			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, bbsno);
			pstmt.setString(2, board.getName());
			pstmt.setString(3, board.getPassword());
			pstmt.setString(4, board.getEmail());
			pstmt.setString(5, board.getSubject());
			pstmt.setString(6, board.getContent());
			pstmt.setInt(7, bbsno);
			pstmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("BoardDAO.insertArticle : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

	}

	public Collection<BoardVO> selectArticleList(int page, int maxno) {
		Connection con = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String sql = "SELECT bbsno, name, subject, writedate, readcount, email, replynumber, replystep, rnum "
				+ "FROM ( "
				+ "SELECT bbsno, name, subject, writedate, readcount, email, replynumber, replystep, ROWNUM rnum "
				+ "FROM ( "
				+ "SELECT bbsno, name, subject, writedate, readcount, email, replynumber, replystep "
				+ "FROM board "
				+ "ORDER BY masterid DESC, replynumber, replystep ) ) "
				+ "WHERE rnum BETWEEN ? AND ?";
		int start = (page - 1) * 10 + 1;
		int end = start + 9;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBbsno(rs.getInt("bbsno"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setWritedate(rs.getDate("writedate"));
				board.setReadcount(rs.getInt("readcount"));
				board.setReplynumber(rs.getInt("replynumber"));
				board.setReplystep(rs.getInt("replystep"));
				board.setSeq(rs.getInt("rnum"));
				list.add(board);

			}

		} catch(Exception e) {
			throw new RuntimeException("BoardDAO.selectArticleList : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

		return list;

	}

	public BoardVO selectArticle(int bbsno) {
		Connection con = null;
		BoardVO board = null;
		String sql = "SELECT * "
				+ "FROM board "
				+ "WHERE bbsno = ?";

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bbsno);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				board = new BoardVO();
				board.setBbsno(rs.getInt("bbsno"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setReadcount(rs.getInt("readcount"));
				board.setWritedate(rs.getDate("writedate"));
				board.setMasterid(rs.getInt("masterid"));
				board.setReplynumber(rs.getInt("replynumber"));
				board.setReplystep(rs.getInt("replystep"));

			}

		} catch(Exception e) {
			throw new RuntimeException("BoardDAO.selectArticle : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

		return board;

	}

	public void updateReadCount(int bbsno) {
		Connection con = null;
		String sql = "UPDATE board "
				+ "SET readcount = readcount + 1 "
				+ "WHERE bbsno = ?";

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bbsno);
			pstmt.executeUpdate();

		} catch(Exception e) {
			throw new RuntimeException("BoardDAO.updateReadCount : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

	}

	public void replyArticle(BoardVO board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			con.setAutoCommit(false);

			String sql1 = "UPDATE board "
					+ "SET replynumber = replynumber + 1 "
					+ "WHERE masterid = ? AND replynumber > ?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, board.getMasterid());
			pstmt.setInt(2, board.getReplynumber());
			pstmt.executeUpdate();

			String sql2 = "SELECT MAX(bbsno) "
					+ "FROM board";
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				board.setBbsno(rs.getInt(1) + 1);

			}

			String sql3 = "INSERT INTO board VALUES ( ?, ?, ?, ?, ?, ?, SYSDATE, ?, 0, ?, ? )";
			pstmt = con.prepareStatement(sql3);
			pstmt.setInt(1, board.getBbsno());
			pstmt.setString(2, board.getName());
			pstmt.setString(3, board.getPassword());
			pstmt.setString(4, board.getEmail());
			pstmt.setString(5, board.getSubject());
			pstmt.setString(6, board.getContent());
			pstmt.setInt(7, board.getMasterid());
			pstmt.setInt(8, board.getReplynumber() + 1);
			pstmt.setInt(9, board.getReplystep() + 1);
			pstmt.executeUpdate();
			con.commit();

		} catch(Exception e) {
			try {
				con.rollback();

			} catch(SQLException e1) {
				//

			}

			throw new RuntimeException("BoardDAO.replyArticle : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

	}

	public void deleteArticle(int bbsno, int replynumber) {
		String sql = "";
		Connection con = null;

		try {
			con = getConnection();
			if(replynumber > 0) {
				sql = "DELETE FROM board "
						+ "WHERE bbsno = ?";

			} else {
				sql = "DELETE FROM board "
						+ "WHERE masterid = ?";

			}

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bbsno);
			pstmt.executeUpdate();

		} catch(Exception e) {
			throw new RuntimeException("BoardDAO.deleteArticle : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

	}

	public String getPassword(int bbsno) {
		Connection con = null;
		String password = "";
		String sql = "SELECT password "
				+ "FROM board "
				+ "WHERE bbsno = ?";

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bbsno);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				password = rs.getString("password");

			}

		} catch(Exception e) {
			throw new RuntimeException("BoardDAO.getPassword : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

		return password;

	}

	public int selectTotalArticleCount() {
		Connection con = null;
		int count = 0;
		String sql = "SELECT COUNT(bbsno) "
				+ "FROM board";

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt(1);

			}

		} catch(Exception e) {
			throw new RuntimeException("BoardDAO.selectTotalArticleCount : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

		return count;

	}

	public void updateArticle(BoardVO board) {
		Connection con = null;
		String sql = "UPDATE board "
				+ "SET name = ?, email = ?, subject = ?, content = ?, writedate = SYSDATE "
				+ "WHERE bbsno = ?";

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getEmail());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setInt(5, board.getBbsno());
			pstmt.executeQuery();

		} catch(Exception e) {
			throw new RuntimeException("BoardDAO.updateArticle : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

	}

	public int selectTotalBbsCount() {
		Connection con = null;
		String sql = "SELECT COUNT(bbsno) "
				+ "FROM board";

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int bbsCount = rs.getInt(1);

			return bbsCount;

		} catch(Exception e) {
			throw new RuntimeException("BoardDAO.selectTotalBbsCount : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

	}

}
