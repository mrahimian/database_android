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
    ListView listView;
    InfoAdapter infoAdapter;
    String carName;
    String carCode;
    String goodsName;
    String goodsCode;
    String brandName;
    String brandCode;
    boolean finishProgress = false;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.listview);
        info = new ArrayList<>();
        Intent intent = getIntent();
        carName = intent.getStringExtra("carName");
        carCode = intent.getStringExtra("carCode");
        goodsName = intent.getStringExtra("goodsName");
        goodsCode = intent.getStringExtra("goodsCode");
        brandName = intent.getStringExtra("brandName");
        brandCode = intent.getStringExtra("brandCode");
        StartConnecting st = new StartConnecting(this);
        st.execute();
        refreshDisplay();
    }

    void refreshDisplay() {
        infoAdapter = new InfoAdapter(this, info);
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
            String carQuery = "(AvalableCar like '%"+carCode+";%')";
            String goodsQuery = "(Category like '%"+goodsCode+";%')";
            String brandQuery = "(AvalableBrand like '%"+brandCode+"%' or AvalableBrand COLLATE Persian_100_CI_AS like N'%"+brandName+"%')";

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

                String queryStmt = "select * from dbo.MidStore where " + carQuery + " and " + goodsQuery + " and " + brandQuery;

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
                    String star = rslt.getString(3);
                    String description = rslt.getString(33);
                    Info inf = new Info("کد نمایندگی : " + id , phone , phone2,phone3, "ناحیه : " + region,"ستاره : " + isStar,"علت ستاره : " + star,"توضیحات : " + description,"دارد : ","ندارد : ");
                    info.add(inf);
                }
                stmt.close();
                conn.close();
                rslt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (ClassNotFoundException e) {
                Log.e("ERRO", e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
                pd.dismiss();
                refreshDisplay();
            super.onPostExecute(result);
        }
    }

}