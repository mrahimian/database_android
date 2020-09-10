package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Edition extends AppCompatActivity {
    ListView lv;
    ArrayList<String> inf = new ArrayList<>() ;
    ArrayAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);
        lv = findViewById(R.id.lv);
        Start start = new Start(this);
        start.execute();
    }

    private class Start extends AsyncTask<String,Void,String> {
        ProgressDialog pd;
        Context context;
        Start(Context context){
            this.context = context;
        }
        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(context);
            pd.setMessage("لطفا صبر کنید ...");
            pd.setCancelable(false);
            pd.show();
        }


        @Override
        protected String doInBackground(String... strings) {
            String _user = "sa";
            String _pass = "hamdi@0912";
            String _DB = "yadak";
            String _server = "176.9.199.181";
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection conn = null;
            String ConnURL = null;
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                ConnURL = "jdbc:jtds:sqlserver://" + _server + ";"
                        + "databaseName=" + _DB + ";user=" + _user + ";password="
                        + _pass + ";";
                conn = DriverManager.getConnection(ConnURL);

                String queryStmt = "select * from dbo.users";

                Statement stmt = conn.createStatement();
                ResultSet rslt = stmt.executeQuery(queryStmt);

                while(rslt.next())
                {
                    String info = rslt.getString(1);
                    inf.add(info);
                }
                stmt.close();
                conn.close();
                rslt.close();
            }  catch (Exception e) {
//                Toast.makeText(context,"PLEASE CHECK YOUR CONNECTION!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            refreshDisplay();
            super.onPostExecute(s);
        }

        void refreshDisplay() {
            ad = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, inf);
            lv.setAdapter(ad);
        }
    }

}