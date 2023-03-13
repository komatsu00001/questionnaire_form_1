<%@page import="model.SurveyDto"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
//アンケート結果を１レコードづつ繰り返し出力
//リクエストスコープからテーブルデータ全件
List<SurveyDto> list = (List<SurveyDto>)request.getAttribute("ALL_SURVEY_LIST");
%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>アンケート回答一覧</title>
  </head>
  <body>
    <h2>アンケート回答一覧</h2>
    <table class="surbey_list" border="1">
      <tr bgcolor="#c0c0c0">
        <th>名前</th>
        <th>年齢</th>
        <th>性別</th>
        <th>満足度</th>
        <th>ご意見・ご感想</th>
      </tr>
<%
for (int i = 0; i < list.size(); i++) {
	SurveyDto dto = list.get(i);
%>
      <tr>
        <td><%= replaceEscapeChar(dto.getName()) %></td>
        <td><%= dto.getAge() %></td>
<%
	if(dto.getSex() == 1){
%>
		<td>オス</td>
<%
	} else {
%>
		<td>メス</td>
<%
	}
	switch(dto.getSatisfactionLevel()) {
	case 1:
%>
        <td>とても不満</td>
<%
		break;
	case 2:
%>
        <td>不満</td>
<%
		break;
	case 3:
%>
        <td>普通</td>
<%
		break;
	case 4:
%>
        <td>満足</td>
<%
		break;
	case 5:
%>
        <td>とても満足</td>
<%
		break;
	}
%>

        <td><%= replaceEscapeChar(dto.getMessage()) %></td>
      </tr>
<%
}
%>
    </table>
    <a href="<%= request.getContextPath() %>>/InputSurvey">回答画面に戻る</a>
  </body>
</html>
<%!
//文字列データのエスケープ（名前、ご意見・ご感想）
String replaceEscapeChar (String inputText) {

	String charAfterEscape = inputText;

	charAfterEscape = charAfterEscape.replace("&", "&amp;");
	charAfterEscape = charAfterEscape.replace("<", "&lt;");
	charAfterEscape = charAfterEscape.replace(">", "&gt;");
	charAfterEscape = charAfterEscape.replace("\"", "&quot;");
	charAfterEscape = charAfterEscape.replace("'", "&#039;");
	return charAfterEscape;
}
%>