package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDao {

	//ドライバ情報、接続先情報
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	String JDBD_URL    = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
	String USER_ID     = "test_user";
	String USER_PASS   = "test_pass";

	public UserInfoDto doSelect( String userId, String passWord ) {
		//ドライバのロード
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//接続時データ格納変数
		Connection        con = null;
		PreparedStatement ps  = null;
		ResultSet         rs  = null;

		//戻り値の格納用変数
		UserInfoDto dto = new UserInfoDto();

		try {
			con = DriverManager.getConnection(JDBD_URL, USER_ID, USER_PASS);

			//SQL文作成
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT                       ");
			buf.append("      USER_ID,                ");
			buf.append("     USER_NAME,               ");
			buf.append("     PASSWORD                 ");
			buf.append(" FROM USER_INFO               ");
			buf.append(" WHERE USER_ID  = ?           ");
			buf.append(" AND   PASSWORD = ?         ; ");
			ps = con.prepareStatement(buf.toString());
			ps.setString(1, userId);
			ps.setString(2, passWord);
			//送受信
			rs = ps.executeQuery();
			//データ抽出
			if (rs.next()) {
				dto.setUserId(  rs.getString("USER_ID"));
				dto.setUserName(rs.getString("USER_NAME"));
				dto.setPassWord(rs.getString("PASSWORD"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//接続の解除
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//戻し
		return dto;
	}
}
