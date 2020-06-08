package com.example.graduationapplication.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.DialogAction;


public class BaseActivity extends AppCompatActivity {
    protected AppCompatActivity activity;
    MaterialDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public MaterialDialog showMessage (String title , String message , String ok,MaterialDialog.SingleButtonCallback click){

        dialog= new MaterialDialog.Builder(BaseActivity.this).title(title).content(message)
                .positiveText(ok).onPositive(click).show();


        return dialog;
    }

    public MaterialDialog askForAction (String title , String message , String ok,MaterialDialog.SingleButtonCallback click,String no,MaterialDialog.SingleButtonCallback click2){

        dialog= new MaterialDialog.Builder(BaseActivity.this).title(title).content(message)
                .positiveText(ok).onPositive(click).negativeText(no).onNegative(click2).show();


        return dialog;
    }
    public MaterialDialog showProgressBar (){
        dialog=new MaterialDialog.Builder(BaseActivity.this).progress(true,0)
                .cancelable(false).show();
        return dialog;

    }
    public void hideProgressBar(){
        if(dialog!=null&&dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
