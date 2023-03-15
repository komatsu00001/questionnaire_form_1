<%@page import="model.UserInfoDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
//ログインしたユーザーの名前を取得
UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");
String userName = userInfoOnSession.getUserName();
%>

<html>
  <head>
    <meta charset="UTF-8">
    <title>アンケートフォーム</title>
  </head>
  <body>
    <h2>アンケートフォーム</h2>
    <form action="SaveSurvey" method="post">
      <p>名前：<%= userName %>
        <input type="hidden" name="NAME" value="<%= userName %>">
      </p>
      <p>年齢：
        <input type="number" name="AGE" maxlength="3" id="ID_AGE">
      </p>
      <p>性別：
        <input type="radio" name="SEX" value="1" checked>男
        <input type="radio" name="SEX" value="2">女
      </p>
      <p>満足度：
        <select name="SATISFACTION_LEVEL">
          <option value="5">とても満足</option>
          <option value="4">満足</option>
          <option value="3">普通</option>
          <option value="2">不満</option>
          <option value="1">とても不満</option>
        </select>
      </p>
      <p>ご意見・ご感想：<br>
        <textarea name="MESSAGE" id="ID_MESSAGE" maxlength="250" cols="30" rows="10"></textarea>
      </p>
      <input type="submit" value="回答する（SaveSurvey起動）" id="ID_SUBMIT">
    </form>
    <script type="text/javascript" src="js/input_survey.js"></script>
    <br>
    <a href="ShowAllSurvey">回答一覧画面へ</a>
    <br>
    <a href="ExecuteLogout">ログアウトする</a>
  </body>
</html>