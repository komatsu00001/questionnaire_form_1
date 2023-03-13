/**
 *ログイン画面の未入力時の警告
 */
var elmSubmit = document.getElementById("ID_SUBMIT");
elmSubmit.onclick = function(){
	var elmUserId   = document.getElementById("ID_USER_ID");
	var elmPassword = document.getElementById("ID_PASSWORD");
	var canSubmit = true;
	if ( elmUserId.value == "" || elmPassword.value == "" ) {
		alert("未入力の項目があります");
		canSubmit = false;
	}
	return canSubmit;
}


