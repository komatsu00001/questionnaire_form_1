package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ShowAllSurveyBL;
import model.SurveyDto;
import model.UserInfoDto;

//データ取得し、データ一覧を画面表示するクラス
public class ShowAllSurvey extends HttpServlet {
	public static final long serialVersionUID = 1L;
	public ShowAllSurvey() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//文字コードセット（レスポンスのみ）
		resp.setContentType("text/html;charset=UTF-8");
		//セッションオブジェクト取得
		HttpSession session = req.getSession();

		//ログイン情報DTOの取得
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態の有無
		if ( userInfoOnSession != null ) {
			//ログイン済み（回答画面一覧へ）

			//dtoリストインスタンス
			List<SurveyDto> list = new ArrayList<SurveyDto>();
			//BLインスタンス
			ShowAllSurveyBL logic = new ShowAllSurveyBL();
			//BLからのデータをリストに格納
			list = logic.executeSelectSurvey();

			//名前とリストをリクエストにセット
			req.setAttribute("ALL_SURVEY_LIST", list);

			//フォワード先リクエストに指定
			RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/view/show_all_survey.jsp");
			//viewにフォワード
			dispatch.forward(req, resp);

		} else {
			//未ログイン（ログイン画面へ）
			resp.sendRedirect("Login");
		}
	}

}
