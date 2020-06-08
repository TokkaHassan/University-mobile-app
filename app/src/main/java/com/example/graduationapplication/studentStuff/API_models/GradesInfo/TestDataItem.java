package com.example.graduationapplication.studentStuff.API_models.GradesInfo;

import com.google.gson.annotations.SerializedName;

public class TestDataItem{

	@SerializedName("course_id")
	private String courseId;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("grade")
	private String grade;

	@SerializedName("student_id")
	private String studentId;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id")
	private String id;

	public void setCourseId(String courseId){
		this.courseId = courseId;
	}

	public String getCourseId(){
		return courseId;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setGrade(String grade){
		this.grade = grade;
	}

	public String getGrade(){
		return grade;
	}

	public void setStudentId(String studentId){
		this.studentId = studentId;
	}

	public String getStudentId(){
		return studentId;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"TestDataItem{" + 
			"course_id = '" + courseId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",grade = '" + grade + '\'' + 
			",student_id = '" + studentId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}