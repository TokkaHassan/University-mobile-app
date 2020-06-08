package com.example.graduationapplication.studentStuff.API_models.SubjectsTable;

import com.google.gson.annotations.SerializedName;

public class TestDataItem{

	@SerializedName("hours")
	private String hours;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("dep")
	private Object dep;

	public void setHours(String hours){
		this.hours = hours;
	}

	public String getHours(){
		return hours;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setDep(Object dep){
		this.dep = dep;
	}

	public Object getDep(){
		return dep;
	}

	@Override
 	public String toString(){
		return 
			"TestDataItem{" + 
			"hours = '" + hours + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",dep = '" + dep + '\'' + 
			"}";
		}
}