package model;

public class SaveSurveyBL {
	public boolean executeInsertSurvey(SurveyDto dto) {
		//成功フラグ
		boolean succesInsert = false;
		SurveyDao dao = new SurveyDao();
		succesInsert = dao.doInsert(dto);
		return succesInsert;
	}
}
