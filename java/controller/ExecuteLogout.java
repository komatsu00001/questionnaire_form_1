package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDto;

//セッションの破棄、ログアウト画面表示クラス
public class ExecuteLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteLogout() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//文字コードセット（レスポンスのみ）
		resp.setContentType("text/html;charset=UTF-8");
		//セッションオブジェクト生成
		HttpSession session = req.getSession();
		//セッションからDTOオブジェクト取得
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");
		//ログイン状態の有無
		if (userInfoOnSession != null ) {
			//ログイン済み（ログアウト処理＆画面遷移）

			//セッションデータの破棄
			session.invalidate();
			//フォワード先取得
			RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/view/execute_logout.jsp");
			//viewにフォワード
			dispatch.forward(req, resp);
		} else {
			//未ログイン（ログイン画面へ）
			resp.sendRedirect("Login");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
