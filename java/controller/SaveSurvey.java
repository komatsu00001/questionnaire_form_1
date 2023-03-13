package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SaveSurveyBL;
import model.SurveyDto;
import model.UserInfoDto;

//データ保存クラス
//アンケート結果をDBに保存
public class SaveSurvey extends HttpServlet {
	public static final long serialVersionUID = 1L;
	public SaveSurvey() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードセット（レスポンス、リクエスト）
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//リクエストからセッションの生成
		HttpSession session = request.getSession();
		//セクションからDTOを取得
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態の有無
		if (userInfoOnSession != null) {
			//ログイン済み（データの登録、画面遷移）

			//成功フラグ定義
			boolean succesFlg = true;

			//バリデーションチェック（未入力などを除外）
			if ( !(
					validatePrmName   (request.getParameter("NAME")) &&
					validatePrmAge    (request.getParameter("AGE")) &&
					validatePrmSex    (request.getParameter("SEX")) &&
					validatePrmSatLv  (request.getParameter("SATISFACTION_LEVEL")) &&
					validatePrmMessage(request.getParameter("MESSAGE"))
					) ) {
				//NG　（false）
				succesFlg = false;

			} else {
				//OK　（データ登録）

				//パラメータ取得
				String name           =                   request.getParameter("NAME");
				int    age            = Integer.parseInt(request.getParameter("AGE"));
				int    sex            = Integer.parseInt(request.getParameter("SEX"));
				int satisfactionLevel = Integer.parseInt(request.getParameter("SATISFACTION_LEVEL"));
				String message        =                   request.getParameter("MESSAGE");

				//dtoにデータセット
				SurveyDto dto = new SurveyDto();
				dto.setName(name);
				dto.setAge(age);
				dto.setSex(sex);
				dto.setSatisfactionLevel(satisfactionLevel);
				dto.setMessage(message);
				dto.setTime(new Timestamp(System.currentTimeMillis()));

				//BL起動、戻りでフラグを更新
				SaveSurveyBL logic = new SaveSurveyBL();
				succesFlg          = logic.executeInsertSurvey(dto);
			}

			//if文で成功フラグの結果
			if ( succesFlg ) {
				//成功　（回答完了HTML）
				response.sendRedirect("htmls/finish.html");
			} else {
				//失敗　（エラー画面HTML）ｊｓではじかれる
				response.sendRedirect("htmls/error.html");
			}
		} else {
			//未ログイン　（ログイン画面へ）
			response.sendRedirect("Login");
		}
	}
	//名前　（nullまたは空白）
	private boolean validatePrmName( String pr ) {
		boolean validateResult = true;
		if ( pr == null || pr.equals("") ) {
			validateResult = false;
		}
		return validateResult;
	}
	//年齢　（nullまたは正の数以外）
	private boolean validatePrmAge( String pr ) {
		boolean validateResult = true;
		if ( pr == null || !(pr.matches("^[0-9]+$")) ) {
			validateResult = false;
		}
		return validateResult;
	}
	//性別　（nullまたは１、２以外）
	private boolean validatePrmSex( String pr ) {
		boolean validateResult = true;
		if ( pr == null || !( pr.equals("1") || pr.equals("2") ) ) {
			validateResult = false;
		}
		return validateResult;
	}
	//満足度（nullまたは１，２，３，４，５以外）
	private boolean validatePrmSatLv( String pr ) {
		boolean validateResult = true;
		if ( pr == null || !( pr.equals("1") || pr.equals("2") || pr.equals("3") || pr.equals("4") || pr.equals("5") ) ) {
			validateResult = false;
		}
		return validateResult;
	}
	//ご意見（nullまたは空白）
	private boolean validatePrmMessage( String pr ) {
		boolean validateResult = true;
		if ( pr == null || pr.equals("") ) {
			validateResult = false;
		}
		return validateResult;
	}
}
