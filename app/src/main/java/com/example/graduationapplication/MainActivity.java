package com.example.graduationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.graduationapplication.Base.BaseActivity;
import com.example.graduationapplication.studentStuff.APIManager;
import com.example.graduationapplication.studentStuff.API_models.Registeration.RegisteredUsers;
import com.example.graduationapplication.studentStuff.API_models.Registeration.TestDataItem;
import com.example.graduationapplication.studentStuff.API_models.StudentsInfo.StudentInfo;
import com.example.graduationapplication.studentStuff.Activities.Attendance;
import com.example.graduationapplication.studentStuff.Activities.Entrance_student;
import com.example.graduationapplication.studentStuff.SignIn;
import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;
import com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs.professorsDAO;
import com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs.studentsDAO;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.messages;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.professors;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.students;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.graduationapplication.SessionManager.ID;
import static com.example.graduationapplication.SessionManager.Role;
import static com.example.graduationapplication.SessionManager.tableId;


public class MainActivity extends BaseActivity {

    public Button mSignUpBtn;
    Button login_btn;
    EditText username;
    EditText password;
    TextInputLayout usernameWrapper;
    TextInputLayout passwordWrapper;
    private EditText mEmail;
    private TextInputLayout mEmailWrapper;
    boolean found;
    SessionManager sessionManager;
    String name;
    List<students> studentsList;
    String tableID;
    students theOne;
    int check=0;
    String iden;
    String role;
    Random random=new Random();
    List<professors> professorsList;
    professors theProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating the layout design
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_btn = findViewById(R.id.login_btn);
        usernameWrapper = findViewById(R.id.usernameWrapper);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordWrapper = findViewById(R.id.passwordWrapper);

        mEmail = (EditText) findViewById(R.id.email);
        mEmailWrapper = (TextInputLayout) findViewById(R.id.email_Wrapper);
        sessionManager = new SessionManager(getApplicationContext());
        mSignUpBtn =  findViewById(R.id.signUp_btn);

        MyDatabase.getProfessorsBranch().addValueEventListener(valueEventListenerProf);
        MyDatabase.getStudentsBranch().addValueEventListener(valueEventListener);

        Log.e("role check",sessionManager.getRole());
    }

    public void onLoginBtnClicked(View view) throws InvalidKeySpecException, NoSuchAlgorithmException, InterruptedException {
        showProgressBar();
        final String userId = usernameWrapper.getEditText().getText().toString();
        final String password = passwordWrapper.getEditText().getText().toString();
        final String email = mEmailWrapper.getEditText().getText().toString();
        String strongPass = SignIn.generateStorngPasswordHash(password);
        students student=null;
        //Log.e("pass.", strongPass);
        found = false;
        check++;

if (sessionManager.getRole().equals("student")) {

hideProgressBar();
    if (studentsList != null) {
        for (students item : studentsList) {
            if (item.getTheE_mail().equals(email)&& item.getId().equals(userId)) {
                if (SignIn.validatePassword(password, item.getPassword())) {
                    student = item;
                    found = true;
                }

                break;
            }
        }
    }


        if (!found) {
            hideProgressBar();
            showMessage("Error", "Wrong UserID, Password or E-mail", "OK", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                }
            });
        } else {
            hideProgressBar();
            sessionManager.createLoginSession(student.getName(), student.getTheE_mail(),
                    student.getId());
            sessionManager.editor.putString(tableId,student.getTableid()).commit();
            Intent intent = new Intent(MainActivity.this, Entrance_student.class);
            startActivity(intent);
            finish();
        }

    }
else
{
hideProgressBar();

    if (professorsList != null) {
        for (professors item : professorsList) {
            if (item.getEmail().equals(email)&& item.getUniID().equals(userId)) {
                if (SignIn.validatePassword(password, item.getPassword())) {
                 theProf = item;
                    found = true;
                    break;
                }


            }
        }
    }


    if (!found) {
            hideProgressBar();
            showMessage("Error", "Wrong UserID, Password or E-mail", "OK", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                }
            });
        } else {
            hideProgressBar();
            sessionManager.createLoginSession(theProf.getName(), theProf.getEmail(),
                    theProf.getUniID());
            Intent intent = new Intent(MainActivity.this, Attendance.class);
            startActivity(intent);
            finish();
        }
    }


    }



    public void onSignBtnClicked (View view) throws InvalidKeySpecException, NoSuchAlgorithmException {
        final String id = usernameWrapper.getEditText().getText().toString();
        final String password = passwordWrapper.getEditText().getText().toString();
        final String e_mail = mEmailWrapper.getEditText().getText().toString();
        final String strongPass = SignIn.generateStorngPasswordHash(password);
        showProgressBar();


        APIManager.getAPIs().getStudentInfoCalls().enqueue(new Callback<StudentInfo>() {
            @Override
            public void onResponse(Call<StudentInfo> call, Response<StudentInfo> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    List<com.example.graduationapplication.studentStuff.API_models.StudentsInfo.TestDataItem> users = response.body().getTestData();
                    for (com.example.graduationapplication.studentStuff.API_models.StudentsInfo.TestDataItem item : users){
                        if(item.getSid().equals(id)){
                            name=item.getName();
                            tableID=item.getId();
                            found=true;
                            Log.e("user api","entered");
                            break;
                        }
                    }
                    if(found && (sessionManager.getRole().equals("student"))){
                    students student = new students(tableID,id,name,strongPass,e_mail);
                    studentsDAO.InsertStudent(student);
                        sessionManager.createLoginSession(name, e_mail,id);
                        sessionManager.editor.putString(tableId,student.getTableid()).commit();
                        showMessage("Congratulations", "You have been registered in the application", "OK", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Intent intent = new Intent(MainActivity.this, Entrance_student.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });


                    }else if(found && (sessionManager.getRole().equals("doctor"))){
                        professors professor=new professors(name,id,e_mail,strongPass);
                        professor.setMobile(random.nextInt(99999999)+"");
                        professorsDAO.InsertProfessor(professor);
                        showMessage("Congratulations", "You have been registered in the application", "OK", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Intent intent = new Intent(MainActivity.this, Attendance.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });
                    }
                    else {
                        showMessage("Violation", "This user id is not found in the university database", "OK", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        });
                    }

                }

            }

            @Override
            public void onFailure(Call<StudentInfo> call, Throwable t) {

                hideProgressBar();
                showMessage("Error", "A problem occurred with connection", "ok", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        dialog.dismiss();
                    }
                });
            }
        });

    }



    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            studentsList= new ArrayList<>();
            for(DataSnapshot item:dataSnapshot.getChildren()){

                    theOne=item.getValue(students.class);
                studentsList.add(theOne);
                //entered=true;
                Log.e("null list",theOne.toString());
            }


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

            hideProgressBar();
            showMessage("Error", "A problem occurred with connection", "ok", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    dialog.dismiss();
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyDatabase.getStudentsBranch().removeEventListener(valueEventListener);
        MyDatabase.getProfessorsBranch().removeEventListener(valueEventListenerProf);

    }
    ValueEventListener valueEventListenerProf=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            professorsList=new ArrayList<>();
            for(DataSnapshot item:dataSnapshot.getChildren()){
                professors professor=item.getValue(professors.class );
                professorsList.add(professor);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            hideProgressBar();
            showMessage("Error", "A problem occurred with connection", "ok", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    dialog.dismiss();
                }
            });
        }
    };
}
