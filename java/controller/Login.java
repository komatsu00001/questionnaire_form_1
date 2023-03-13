package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDto;

//ログイン画面レスポンスクラス
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Login () {
		super();
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//レスポンス文字コード
		resp.setContentType("text/html;charset=UTF-8");

		//セッション生成
		HttpSession session = req.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態の確認
		if (userInfoOnSession != null) {
			//ログイン済み（アンケートフォームへ）
			resp.sendRedirect("InputSurvey");
		} else {
			//未ログイン  （ログイン画面へ）
			RequestDispatcher dispatch = req.getRequestDispatcher("WEB-INF/view/login.jsp");
			dispatch.forward(req, resp);
		}

	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
