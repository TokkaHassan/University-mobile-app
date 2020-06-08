package com.example.graduationapplication.studentStuff.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.graduationapplication.Base.BaseActivity;
import com.example.graduationapplication.R;
import com.example.graduationapplication.SessionManager;
import com.example.graduationapplication.studentStuff.Adapters_student.Adapter_messages;
import com.example.graduationapplication.studentStuff.firebaseUtils.MyDatabase;
import com.example.graduationapplication.studentStuff.firebaseUtils.firebase_DAOs.messages_DAO;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.chatRooms;
import com.example.graduationapplication.studentStuff.firebaseUtils.models.messages;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class chat extends BaseActivity {

    public EditText mChatInput;
    public ImageView mSendButton;
    public RecyclerView mMessageRecycler;
    Adapter_messages adapter_messages;
    RecyclerView.LayoutManager layoutManager;
    String chatID;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating the layout design

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        sessionManager=new SessionManager(getApplicationContext());
        Intent i = getIntent();
        chatID = i.getStringExtra("chatID");
        mChatInput = (EditText) findViewById(R.id.chat_input);
        mSendButton = (ImageView) findViewById(R.id.send_button);
        mMessageRecycler = (RecyclerView) findViewById(R.id.message_recycler);
        adapter_messages=new Adapter_messages(null,getApplicationContext());
        adapter_messages.setOnMessageClicked(new Adapter_messages.onItemClickListener() {
            @Override
            public void onItemClicked(messages message) {
                showMessage("Message Info.", "Sent by: " + message.getSenderName(), "ok", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                });
            }
        });
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        mMessageRecycler.setAdapter(adapter_messages);
        mMessageRecycler.setLayoutManager(layoutManager);


        messages_DAO.getMessagesByRoom(chatID).addValueEventListener(valueEventListener);
    }

    public void onSendButtonClicked(View view) {
        String content = mChatInput.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY  hh:mm");
        String currentDateAndTime = sdf.format(new Date());

        messages message = new messages();
        message.setChatRoomID(chatID);
        message.setSenderID(sessionManager.getID());
        message.setSenderName(sessionManager.getName());
        message.setText(content);
        message.setTimeStamp(currentDateAndTime);
        messages_DAO.InsertMessage(message);
        mChatInput.setText("");

    }

    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            List<messages> messagesList=new ArrayList<>();
            for(DataSnapshot item:dataSnapshot.getChildren()){
                messages message=item.getValue(messages.class);
                messagesList.add(message);
            }
            adapter_messages.changeData(messagesList);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyDatabase.getMessagesBranch().removeEventListener(valueEventListener);
    }
}
