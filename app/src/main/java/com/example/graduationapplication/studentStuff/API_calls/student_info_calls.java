package com.example.graduationapplication.studentStuff.API_calls;

import com.example.graduationapplication.studentStuff.API_models.GradesInfo.TheGrades;
import com.example.graduationapplication.studentStuff.API_models.Registeration.RegisteredUsers;
import com.example.graduationapplication.studentStuff.API_models.StudentsInfo.StudentInfo;
import com.example.graduationapplication.studentStuff.API_models.SubjectsTable.Subjects;
import com.example.graduationapplication.studentStuff.Activities.Grades;

import retrofit2.Call;
import retrofit2.http.GET;

public interface student_info_calls {

    @GET("gpa_api.php")
    Call<StudentInfo> getStudentInfoCalls();

    @GET("users_api.php")
    Call<RegisteredUsers> getRegisteredUsersCalls();

    @GET("grades_api.php")
    Call<TheGrades> getStudentsGradesCalls();

    @GET("info_api.php")
    Call<Subjects> getSubjectTableCalls ();
}
