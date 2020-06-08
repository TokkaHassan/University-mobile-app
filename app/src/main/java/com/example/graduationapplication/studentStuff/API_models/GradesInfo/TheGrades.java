package com.example.graduationapplication.studentStuff.API_models.GradesInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TheGrades{

	@SerializedName("testData")
	private List<TestDataItem> testData;

	public void setTestData(List<TestDataItem> testData){
		this.testData = testData;
	}

	public List<TestDataItem> getTestData(){
		return testData;
	}

	@Override
 	public String toString(){
		return 
			"TheGrades{" + 
			"testData = '" + testData + '\'' + 
			"}";
		}
}