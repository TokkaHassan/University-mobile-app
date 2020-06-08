package com.example.graduationapplication.studentStuff.API_models.Registeration;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TestDataItem {

	@SerializedName("password")
	private String password;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("id")
	private String id;

	@SerializedName("remember_token")
	private Object rememberToken;

	@SerializedName("email")
	private String email;

	@SerializedName("current_c")
	private String currentC;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setEmailVerifiedAt(Object emailVerifiedAt){
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setRememberToken(Object rememberToken){
		this.rememberToken = rememberToken;
	}

	public Object getRememberToken(){
		return rememberToken;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setCurrentC(String currentC){
		this.currentC = currentC;
	}

	public String getCurrentC(){
		return currentC;
	}

	@Override
 	public String toString(){
		return 
			"users{" +
			"password = '" + password + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",email_verified_at = '" + emailVerifiedAt + '\'' + 
			",id = '" + id + '\'' + 
			",remember_token = '" + rememberToken + '\'' + 
			",email = '" + email + '\'' + 
			",current_c = '" + currentC + '\'' + 
			"}";
		}
}