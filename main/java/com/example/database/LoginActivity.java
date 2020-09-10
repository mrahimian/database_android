package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    HashMap<String,String> users;
    EditText username;
    EditText password;
    Button login;
    static String user = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        setUsers();
    }

    void setUsers(){
        users = new HashMap<>();
        for (int j = 101; j <= 130 ; j++) {
            users.put("user" + j, "" + j) ;
        }
    }

    public void login(View v){
        String un = username.getText().toString();
        String pw = password.getText().toString();
        if (!users.containsKey(un)){
            Toast toast = Toast.makeText(this,"نام کاربری صحیح نمی باشد",Toast.LENGTH_SHORT);
            toast.getView().setBackgroundColor(0xfff44336);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 200);
            toast.show();
            return;
        }
        if (!users.get(un).equals(pw)) {
            Toast toast = Toast.makeText(this, "نام کاربری و رمز عبور مطابقت ندارند", Toast.LENGTH_SHORT);
            toast.getView().setBackgroundColor(0xfff44336);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 200);
            toast.show();
            return;
        }
        user = username.getText().toString();
        startActivity(new Intent(LoginActivity.this,ChooseActivity.class));
    }
}