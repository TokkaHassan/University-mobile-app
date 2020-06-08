package com.example.graduationapplication.studentStuff.API_models.StudentsInfo;

import com.google.gson.annotations.SerializedName;

public class TestDataItem{

	@SerializedName("role")
	private String role;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private Object phone;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("name")
	private String name;

	@SerializedName("gpa")
	private String gpa;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("current_c")
	private String currentC;

	@SerializedName("sid")
	private String sid;

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPhone(Object phone){
		this.phone = phone;
	}

	public Object getPhone(){
		return phone;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setGpa(String gpa){
		this.gpa = gpa;
	}

	public String getGpa(){
		return gpa;
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

	public void setCurrentC(String currentC){
		this.currentC = currentC;
	}

	public String getCurrentC(){
		return currentC;
	}

	public void setSid(String sid){
		this.sid = sid;
	}

	public String getSid(){
		return sid;
	}

	@Override
 	public String toString(){
		return 
			"TestDataItem{" + 
			"role = '" + role + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",phone = '" + phone + '\'' + 
			",user_id = '" + userId + '\'' + 
			",name = '" + name + '\'' + 
			",gpa = '" + gpa + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",current_c = '" + currentC + '\'' + 
			",sid = '" + sid + '\'' + 
			"}";
		}
}