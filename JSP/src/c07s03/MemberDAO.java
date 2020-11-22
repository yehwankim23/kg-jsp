package c07s03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	static {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("드라이버 로드 성공");

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}

	}

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

	private void closeConnection (Connection con) {
		if(con != null) {
			try {
				con.close();

			} catch(Exception e) {
				//

			}

		}

	}

	public void insert (MemberVO member) {
		Connection con = null;

		try {
			con = getConnection();
			String sql = "INSERT INTO member VALUES (" + 
					"    ?," + 
					"    ?," + 
					"    ?," + 
					"    ?," + 
					"    ?" + 
					")";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getAddress());
			pstmt.executeQuery();

		} catch(Exception e) {
			throw new RuntimeException("c07s03.MemberDAO.insert :" + e.getMessage());

		} finally {
			closeConnection(con);

		}

	}

	public String getPassword(String userid) {
		String pw = "";
		Connection con = null;

		try {
			con = getConnection();
			String sql = "SELECT" + 
					"    password" + 
					"FROM" + 
					"    member" + 
					"WHERE" + 
					"    userid =?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				pw = rs.getString("password");

			} else {
				throw new RuntimeException("아이디가 존재하지 않습니다");

			}

		} catch(Exception e) {
			throw new RuntimeException("c07s03.MemberDAO.getPassword : " + e.getMessage());

		} finally {
			closeConnection(con);

		}

		return pw;

	}

}
