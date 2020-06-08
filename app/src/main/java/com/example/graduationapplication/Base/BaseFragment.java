package com.example.graduationapplication.Base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

public class BaseFragment extends Fragment {
    BaseActivity activity;
    MaterialDialog dialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= ((BaseActivity) context);
    }

    public MaterialDialog showMessage (String title , String message , String ok,MaterialDialog.SingleButtonCallback click){
        return activity.showMessage(title,message,ok,click);
    }
    public MaterialDialog showProgressBar (){
        return activity.showProgressBar();
    }
    public void hideProgressBar(){
        activity.hideProgressBar();
    }
    public MaterialDialog askForAction (String title , String message , String ok,MaterialDialog.SingleButtonCallback click,String no,MaterialDialog.SingleButtonCallback click2){
        return activity.askForAction(title,message,ok,click,no,click2);
    }
}
