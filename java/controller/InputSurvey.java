package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDto;

//アンケート画面表示クラス
public class InputSurvey extends HttpServlet {
	public static final long serialVersionUID = 1L;
	public InputSurvey() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//文字コードセット（リクエスト、レスポンス）
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		//セクション生成
		HttpSession session = req.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");
		//ログイン状態の有無
		if (userInfoOnSession != null) {
			//ログイン済み（アンケートフォームへ）
			RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/view/input_survey.jsp");
			dispatch.forward(req, resp);
		} else {
			//未ログイン　（ログイン画面へ）
			resp.sendRedirect("Login");
		}
	}
}
