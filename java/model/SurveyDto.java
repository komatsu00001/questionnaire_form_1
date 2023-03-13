package model;

import java.sql.Timestamp;

//surveyテーブルDTO
public class SurveyDto {

	private String    name ;                //名前
	private int       age ;                 //年齢
	private int       sex ;                 //性別
	private int       satisfactionLevel ;   //満足度
	private String    message ;             //メッセージ
	private Timestamp time ;                //更新時刻

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getSatisfactionLevel() {
		return satisfactionLevel;
	}
	public void setSatisfactionLevel(int satisfactionLevel) {
		this.satisfactionLevel = satisfactionLevel;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}


}
