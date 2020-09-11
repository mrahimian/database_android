
package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

    }

    public void searchAndEdit(View v){
        startActivity(new Intent(ChooseActivity.this,MainActivity.class));
    }

    public void createRow(View v){
        startActivity(new Intent(ChooseActivity.this,FullEditActivity.class));
    }

    public void seeEdits(View v){
        if (LoginActivity.user.trim().equals("mahdi")) {
            startActivity(new Intent(ChooseActivity.this, Edition.class));
        }
        else {
            Toast.makeText(this,"دسترسی به این قسمت برای شما مجاز نمی باشد" , Toast.LENGTH_SHORT).show();
        }
    }
}