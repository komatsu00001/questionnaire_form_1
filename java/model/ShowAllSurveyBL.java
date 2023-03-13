package model;

import java.util.ArrayList;
import java.util.List;

public class ShowAllSurveyBL {
	public List<SurveyDto> executeSelectSurvey() {

		List<SurveyDto> dtoList = new ArrayList<SurveyDto>();
		SurveyDao dao = new SurveyDao();
		dtoList = dao.doSelect();

		return dtoList;
	}
}
