package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FullEdit extends AppCompatActivity {
    String id;
    EditText a1;
    EditText a2;
    EditText a3;
    EditText a4;
    EditText a5;
    EditText a6;
    EditText a7;
    EditText a8;
    EditText a9;
    EditText a10;
    EditText a11;
    EditText a12;
    EditText a13;
    EditText a14;
    EditText a15;
    EditText a16;
    EditText a17;
    EditText a18;
    EditText a19;
    EditText a20;
    EditText a21;
    EditText a22;
    EditText a23;
    EditText a24;
    EditText a25;
    EditText a26;
    EditText a27;
    EditText a28;
    EditText a29;
    EditText a30;
    EditText a31;
    EditText a32;
    EditText a33;
    EditText a34;
    EditText a35;
    EditText a36;
    EditText a37;
    EditText a38;
    EditText a39;
    EditText a40;
    EditText a41;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_edit2);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        a1 = findViewById(R.id.new_id);
        a2 = findViewById(R.id.new_is_stared);
        a3 = findViewById(R.id.new_star_des);
        a4 = findViewById(R.id.new_region);
        a5 = findViewById(R.id.new_is_appendix);
        a6 = findViewById(R.id.new_name);
        a7 = findViewById(R.id.new_seller_name);
        a8 = findViewById(R.id.new_phone1);
        a9 = findViewById(R.id.new_phone2);
        a10 = findViewById(R.id.new_mobile);
        a11 = findViewById(R.id.new_website);
        a12 = findViewById(R.id.new_address);
        a13 = findViewById(R.id.new_office_hour);
        a14 = findViewById(R.id.new_part_number);
        a15 = findViewById(R.id.new_delivery);
        a16 = findViewById(R.id.new_refer_acceptance);
        a17 = findViewById(R.id.new_refer_description);
        a18 = findViewById(R.id.new_univesality);
        a19 = findViewById(R.id.new_first_hand);
        a20 = findViewById(R.id.new_referance_date);
        a21 = findViewById(R.id.new_mapper);
        a22 = findViewById(R.id.new_category);
        a23 = findViewById(R.id.new_cat_desc);
        a24 = findViewById(R.id.new_available_car);
        a25 = findViewById(R.id.new_car_desc);
        a26 = findViewById(R.id.new_available_brand);
        a27 = findViewById(R.id.new_brand_desc);
        a28 = findViewById(R.id.new_good_behaviour);
        a29 = findViewById(R.id.new_setter_to_map);
        a30 = findViewById(R.id.new_hard_add);
        a31 = findViewById(R.id.agent);
        a32 = findViewById(R.id.get_to_delivery);
        a33 = findViewById(R.id.new_description);
        a34 = findViewById(R.id.new_exist);
        a35 = findViewById(R.id.new_not_exist);
        a36 = findViewById(R.id.new_ip_price);
        a37 = findViewById(R.id.new_ip_code);
        a38 = findViewById(R.id.new_ip_score);
        a39 = findViewById(R.id.new_ip_immediate);
        a40 = findViewById(R.id.new_behaviour_score);
        a41 = findViewById(R.id.new_total_score);
        StartConnecting sc = new StartConnecting();
        sc.execute();
    }

    private class StartConnecting extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... arg0) {

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

                String queryStmt = "select * from dbo.MidStore where id = " + id;

                Statement stmt = conn.createStatement();
                ResultSet rslt = stmt.executeQuery(queryStmt);

                while(rslt.next())
                {
                    String e1 = rslt.getString(1);
                    String e2 = rslt.getString(2);
                    String e3 = rslt.getString(3);
                    String e4 = rslt.getString(4);
                    String e5 = rslt.getString(5);
                    String e6 = rslt.getString(6);
                    String e7 = rslt.getString(7);
                    String e8 = rslt.getString(8);
                    String e9 = rslt.getString(9);
                    String e10 = rslt.getString(10);
                    String e11 = rslt.getString(11);
                    String e12 = rslt.getString(12);
                    String e13 = rslt.getString(13);
                    String e14 = rslt.getString(14);
                    String e15 = rslt.getString(15);
                    String e16 = rslt.getString(16);
                    String e17 = rslt.getString(17);
                    String e18 = rslt.getString(18);
                    String e19 = rslt.getString(19);
                    String e20 = rslt.getString(20);
                    String e21 = rslt.getString(21);
                    String e22 = rslt.getString(22);
                    String e23 = rslt.getString(23);
                    String e24 = rslt.getString(24);
                    String e25 = rslt.getString(25);
                    String e26 = rslt.getString(26);
                    String e27 = rslt.getString(27);
                    String e28 = rslt.getString(28);
                    String e29 = rslt.getString(29);
                    String e30 = rslt.getString(30);
                    String e31 = rslt.getString(31);
                    String e32 = rslt.getString(32);
                    String e33 = rslt.getString(33);
                    String e34 = rslt.getString(34);
                    String e35 = rslt.getString(35);
                    String e36 = rslt.getString(36);
                    String e37 = rslt.getString(37);
                    String e38 = rslt.getString(38);
                    String e39 = rslt.getString(39);
                    String e40 = rslt.getString(40);
                    String e41 = rslt.getString(41);
                    a1.setText(e1);a2.setText(e2);a3.setText(e3);a4.setText(e4);a5.setText(e5);a6.setText(e6);a7.setText(e7);a8.setText(e8);a9.setText(e9);
                    a10.setText(e10);a11.setText(e11);a12.setText(e12);a13.setText(e13);a14.setText(e14);a15.setText(e15);a16.setText(e16);a17.setText(e17);a18.setText(e18);
                    a19.setText(e19);a20.setText(e20);a21.setText(e21);a22.setText(e22);a23.setText(e23);a24.setText(e24);a25.setText(e25);a26.setText(e26);a27.setText(e27);
                    a28.setText(e28);a29.setText(e29);a30.setText(e30);a31.setText(e31);a32.setText(e32);a33.setText(e33);a34.setText(e34);a35.setText(e35);a36.setText(e36);
                    a37.setText(e37);a38.setText(e38);a39.setText(e39);a40.setText(e40);a41.setText(e41);
                }
                stmt.close();
                conn.close();
                rslt.close();

            }catch (SQLException se){
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }




    }

}