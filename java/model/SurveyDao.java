package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyDao {
	//ドライバ情報、接続先DB、ユーザー名、パスワード
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	String JDBC_URL    = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
	String USER_ID     = "test_user";
	String USER_PASS   = "test_pass";

	//アンケート結果をINSERTするメソッド　
	public boolean doInsert(SurveyDto dto) {

		//ドライバクラスのロード
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//DB接続用変数宣言
		Connection        con = null;
		PreparedStatement ps  = null;


		//成功フラグ
		boolean isSuccess = true;

		try {
			//接続の確立
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//トランザクション開始（オートコミットをoff）
			con.setAutoCommit(false);

			//SQL文作成
			StringBuffer buf = new StringBuffer();
			buf.append("INSERT INTO SURVEY (  ");
			buf.append("  NAME,               ");
			buf.append("  AGE,                ");
			buf.append("  SEX,                ");
			buf.append("  SATISFACTION_LEVEL, ");
			buf.append("  MESSAGE,            ");
			buf.append("  TIME                ");
			buf.append(") VALUES (            ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?                   ");
			buf.append(")                     ");

			//psオブジェクト生成
			ps = con.prepareStatement(buf.toString());

			//DTOのデータをセット
			ps.setString(    1, dto.getName()              ); //第1パラメータ：更新データ（名前）
			ps.setInt(       2, dto.getAge()               ); //第2パラメータ：更新データ（年齢）
			ps.setInt(       3, dto.getSex()               ); //第3パラメータ：更新データ（性別）
			ps.setInt(       4, dto.getSatisfactionLevel() ); //第4パラメータ：更新データ（満足度）
			ps.setString(    5, dto.getMessage()           ); //第5パラメータ：更新データ（メッセージ）
			ps.setTimestamp( 6, dto.getTime()              ); //第6パラメータ：更新データ（更新時刻）

			//実行
			ps.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			//例外処理に失敗フラグを追加
			isSuccess = false;
		} finally {
			//トランザクション終了
			if ( isSuccess ) {
				//コミット実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				//ロールバック実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//接続の解除
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

		//結果を返す
		return isSuccess;
	}

	//surveyテーブルのデータを全権抽出するメソッド
	public List<SurveyDto> doSelect() {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<SurveyDto> dtoList = new ArrayList<SurveyDto>();

		try {
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append("SELECT                ");
			buf.append("  NAME,               ");
			buf.append("  AGE,                ");
			buf.append("  SEX,                ");
			buf.append("  SATISFACTION_LEVEL, ");
			buf.append("  MESSAGE,            ");
			buf.append("  TIME                ");
			buf.append("FROM                  ");
			buf.append("  SURVEY              ");
			buf.append("ORDER BY              ");
			buf.append("  TIME                ");

			ps = con.prepareStatement(buf.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				SurveyDto dto = new SurveyDto();
				dto.setName(             rs.getString(   "NAME"              ));
				dto.setAge(              rs.getInt(      "AGE"               ));
				dto.setSex(              rs.getInt(      "SEX"               ));
				dto.setSatisfactionLevel(rs.getInt(      "SATISFACTION_LEVEL"));
				dto.setMessage(          rs.getString(   "MESSAGE"           ));
				dto.setTime(             rs.getTimestamp("TIME"              ));
				dtoList.add(dto);    //１レコード分格納
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return dtoList;
	}
}
