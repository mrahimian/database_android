package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    ImageView logo ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        logo = findViewById(R.id.start_logo);
        logo.animate().alpha(1.0f).setDuration(3000);
        float x1 =logo.getTranslationX();
        float y1 =logo.getTranslationX();
//        TranslateAnimation tr = new TranslateAnimation(x1,y1,x1,0);
        TranslateAnimation tr = new TranslateAnimation(x1,x1,y1-500,y1);
        tr.setDuration(3000);
        tr.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(FirstActivity.this,LoginActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        logo.startAnimation(tr);
//        startActivity(new Intent(FirstActivity.this,LoginActivity.class));
//        Start st = new Start(this);
    }

    private class Start extends AsyncTask<String,Void,String> {
        Context context;
        Start(Context context){
            this.context = context;
        }
        @Override
        protected void onPreExecute() {
            ProgressDialog pb = new ProgressDialog(context);
            pb.show();
            logo.animate().alpha(1.0f).setDuration(6000);
            logo.animate().translationY(0).setDuration(3000);
            Toast.makeText(context,"jikjdf",Toast.LENGTH_LONG);
        }


        @Override
        protected String doInBackground(String... strings) {

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}