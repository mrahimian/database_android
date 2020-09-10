package com.example.database;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {
    List<Info> info;
    List<Info> clone;
    ListView listView;
    InfoAdapter infoAdapter;
    String carName;
    String carCode;
    String goodsName;
    String goodsCode;
    String brandName;
    String brandCode;
    String[] keys;
    boolean finishProgress = false;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.listview);
        info = new ArrayList<>();
        clone = new ArrayList<>();
        Intent intent = getIntent();
        carName = intent.getStringExtra("carName");
        carCode = intent.getStringExtra("carCode");
        goodsName = intent.getStringExtra("goodsName");
        goodsCode = intent.getStringExtra("goodsCode");
        brandName = intent.getStringExtra("brandName");
        brandCode = intent.getStringExtra("brandCode");
        keys = intent.getStringArrayExtra("key");
        StartConnecting st = new StartConnecting(this);
        st.execute();
        refreshDisplay();
    }

    void refreshDisplay() {
        infoAdapter = new InfoAdapter(this, clone);
        listView.setAdapter(infoAdapter);

    }

    private class StartConnecting extends AsyncTask<String, Void, String> {
        ProgressDialog pd;
        Context context;

        public StartConnecting(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(context);
            pd.setMessage("لطفا صبر کنید ...");
            pd.setCancelable(false);
            pd.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {
            carCode = check(carCode);
            goodsCode = check(goodsCode);
            brandCode = check(brandCode);
            if (!brandCode.equals("") && brandName.equals("")){
                brandName = "----------";
            }
            String carQuery = "(AvalableCar like '%"+carCode+"%')";
            String goodsQuery = "(Category like '%"+goodsCode+"%')";
            String brandQuery = "(AvalableBrand like '%"+brandCode+"%' or AvalableBrand COLLATE Persian_100_CI_AS like N'%"+brandName+"%')";
            String keyQuery = keys.length != 1 ? keyQuery(keys) : "";


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

                String queryStmt = "select * from dbo.MidStore where " + carQuery + " and " + goodsQuery + " and " + brandQuery + keyQuery;

                Statement stmt = conn.createStatement();
                ResultSet rslt = stmt.executeQuery(queryStmt);

                while(rslt.next())
                {
                    String id = rslt.getString(1);
                    String phone = rslt.getString(8);
                    String phone2 = rslt.getString(9);
                    String phone3 = rslt.getString(10);
                    String region = rslt.getString(4);
                    String isStar = rslt.getString(2);
                    if (isStar == null){
                        isStar = "0";
                    }
                    String star = rslt.getString(3);
                    String description = rslt.getString(33);
                    String have = rslt.getString(34);
                    Boolean check = checkExistion(keys,have);
                    String notHave = rslt.getString(35);
                    Info inf = new Info( ""+id , ""+phone , ""+phone2,""+phone3, ""+region,""+isStar,""+star,""+description,""+have, ""+notHave,check);
                    info.add(inf);
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
        protected void onPostExecute(String result) {
                pd.dismiss();
                sort();
                refreshDisplay();
            super.onPostExecute(result);
        }

        String check (String s){
            if (s.equals("")){
                return s;
            }
            return ";" + s + ";";
        }

        String keyQuery(String[] args){
            String query = " and (NotExist COLLATE Persian_100_CI_AS not like N'%";
            for (int i = 0; i < args.length ; i++){
                args[i] = args[i].trim();
                query += (args[i] + "%'");
                if ( i != args.length-1){
                    query += "and NotExist COLLATE Persian_100_CI_AS not like N'%";
                }
            }
            query += ")";
            return query;
        }

        boolean checkExistion(String[] keys , String arg ){
            boolean flag = false;
            for (int i = 0; i < keys.length; i++) {
                if (!keys[i].equals("")){
                    flag = true;
                }
            }
            if(!flag)return false;
            for (String s : keys ){
                if (!arg.contains(s)){
                    return false;
                }
            }
            return true;
        }

        void sort(){
            for (int i = 0; i <  info.size(); i++) {
                if (info.get(i).isExist() && info.get(i).is_star().equals("2")){
                    clone.add(info.get(i));
                }
            }
            for (int i = 0; i <  info.size(); i++) {
                if (info.get(i).isExist() && info.get(i).is_star().equals("1")){
                    clone.add(info.get(i));
                }
            }
            for (int i = 0; i <  info.size(); i++) {
                if (info.get(i).isExist() && info.get(i).is_star().equals("0")){
                    clone.add(info.get(i));
                }
            }
            for (int i = 0; i <  info.size(); i++) {
                if (!info.get(i).isExist() && info.get(i).is_star().equals("2")){
                    clone.add(info.get(i));
                }
            }
            for (int i = 0; i <  info.size(); i++) {
                if (!info.get(i).isExist() && info.get(i).is_star().equals("1")){
                    clone.add(info.get(i));
                }
            }
            for (int i = 0; i <  info.size(); i++) {
                if (!info.get(i).isExist() && info.get(i).is_star().equals("0")){
                    clone.add(info.get(i));
                }
            }
        }
    }

}