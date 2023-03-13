/**
 * アンケートフォームのポップアップ＆中断
 * 年齢　：未入力、数字以外
 * ご意見：未入力
 */
var elmSubmit = document.getElementById("ID_SUBMIT");
elmSubmit.onclick = function(){
	var elmAge   = document.getElementById("ID_AGE");
	var elmMessage = document.getElementById("ID_MESSAGE");
	var canSubmit   = true;
	if( elmAge.value == "" || !(elmAge.value.match("^[0-9]+$")) || elmMessage.value == "" ){
		alert("入力漏れの項目があります");
		canSubmit   = false;
	}
	return canSubmit;
}
