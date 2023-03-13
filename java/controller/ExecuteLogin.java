package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ExecuteLoginBL;
import model.UserInfoDto;

//ログイン実行クラス
public class ExecuteLogin extends HttpServlet {
	public static final long serialVersionUID = 1L;
	public ExecuteLogin () {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//レスポンス、リクエスト文字列
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		//セッション生成
		HttpSession session = req.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態の有無
		if (userInfoOnSession != null) {
			//ログイン済み　（アンケートフォームへ）
			resp.sendRedirect("InputSurvey");
		} else {
			//未ログイン　　（ログイン処理の実行）

			//成功フラグ定義
			boolean successFlg = true;

			//リクエストデータ(name属性)のバリデーションチェック
			if ( !( validatePrmUserId(req.getParameter("USER_ID")) &&
					validatePrmPassword(req.getParameter("PASSWORD")) )  ) {
				//未入力または空白の場合　(false)
				successFlg = false;
			} else {
				//正入力の場合　　　　　　(ログイン処理の実行)
				//リクエストデータ取得
				String userId   = req.getParameter("USER_ID");
				String passWord = req.getParameter("PASSWORD");

				//データをBLに渡す
				UserInfoDto    dto   = new UserInfoDto();
				ExecuteLoginBL logic = new ExecuteLoginBL();
				dto                  = logic.executeSelectUserInfo(userId, passWord);

				if ( dto.getUserId() == null ) {
					//IDのデータnullの場合　（false）
					successFlg = false;

				} else {
					//成功　　　　　　　　　（dtoをセッションに格納しログイン済みにする）

					session.setAttribute("LOGIN_INFO", dto);

				}
			}
			if (successFlg) {
				//フラグ成功　（アンケートフォームへ）
				resp.sendRedirect("InputSurvey");
			} else {
				//フラグ失敗　（ログイン画面へ）
				resp.sendRedirect("Login");
			}

		}
	}
	private boolean validatePrmUserId(String pr) {
		boolean validateResult = true;
		if ( pr == null || pr.equals("") ) {
			validateResult = false;
		}
		return validateResult;
	}
	private boolean validatePrmPassword(String pr) {
			boolean validateResult = true;
		if (pr == null || pr.equals("") ) {
			validateResult = false;
		}
		return validateResult;
	}
}
