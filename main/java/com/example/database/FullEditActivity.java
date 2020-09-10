package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FullEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_edit);
    }

    public void insert (View v){
        EditText a1 = findViewById(R.id.new_id);
        EditText a2 = findViewById(R.id.new_is_stared);
        EditText a3 = findViewById(R.id.new_star_des);
        EditText a4 = findViewById(R.id.new_region);
        EditText a5 = findViewById(R.id.new_is_appendix);
        EditText a6 = findViewById(R.id.new_name);
        EditText a7 = findViewById(R.id.new_seller_name);
        EditText a8 = findViewById(R.id.new_phone1);
        EditText a9 = findViewById(R.id.new_phone2);
        EditText a10 = findViewById(R.id.new_mobile);
        EditText a11 = findViewById(R.id.new_website);
        EditText a12 = findViewById(R.id.new_address);
        EditText a13 = findViewById(R.id.new_office_hour);
        EditText a14 = findViewById(R.id.new_part_number);
        EditText a15 = findViewById(R.id.new_delivery);
        EditText a16 = findViewById(R.id.new_refer_acceptance);
        EditText a17 = findViewById(R.id.new_refer_description);
        EditText a18 = findViewById(R.id.new_univesality);
        EditText a19 = findViewById(R.id.new_first_hand);
        EditText a20 = findViewById(R.id.new_referance_date);
        EditText a21 = findViewById(R.id.new_mapper);
        EditText a22 = findViewById(R.id.new_category);
        EditText a23 = findViewById(R.id.new_cat_desc);
        EditText a24 = findViewById(R.id.new_available_car);
        EditText a25 = findViewById(R.id.new_car_desc);
        EditText a26 = findViewById(R.id.new_available_brand);
        EditText a27 = findViewById(R.id.new_brand_desc);
        EditText a28 = findViewById(R.id.new_good_behaviour);
        EditText a29 = findViewById(R.id.new_setter_to_map);
        EditText a30 = findViewById(R.id.new_hard_add);
        EditText a31 = findViewById(R.id.agent);
        EditText a32 = findViewById(R.id.get_to_delivery);
        EditText a33 = findViewById(R.id.new_description);
        EditText a34 = findViewById(R.id.new_exist);
        EditText a35 = findViewById(R.id.new_not_exist);
        EditText a36 = findViewById(R.id.new_ip_price);
        EditText a37 = findViewById(R.id.new_ip_code);
        EditText a38 = findViewById(R.id.new_ip_score);
        EditText a39 = findViewById(R.id.new_ip_immediate);
        EditText[] newRow = new EditText[]{a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24,a25,a26,a27,a28,a29,a30,a31,a32,a33,a34,a35,a36,a37,a38,a39};
        Edit edit = new Edit(this,newRow);
        edit.execute();


    }

    private class Edit extends AsyncTask<String, Void, String> {

        ProgressDialog pd;
        Context context;
        EditText[] attrs;

        public Edit(Context context, EditText[] current) {
            this.context = context;
            this.attrs = current;
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(context);
            pd.setMessage("در حال ارسال اطلاعات ...");
            pd.setCancelable(false);
            pd.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String _user = "sa";
            String _pass = "hamdi@0912";
            String _DB = "yadak";
            String _server = "176.9.199.181";

            Connection conn = null;
            String ConnURL = null;
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                ConnURL = "jdbc:jtds:sqlserver://" + _server + ";"
                        + "databaseName=" + _DB + ";user=" + _user + ";password="
                        + _pass + ";";
                conn = DriverManager.getConnection(ConnURL);

                Statement stmt = conn.createStatement();

                String q2 = String.format("insert into dbo.MidStore values (%s,%s,N'%s',%s,N'%s',N'%s',N'%s',N'%s',N'%s',N'%s',N'%s',N'%s'," +
                                "%s,%s,N'%s',%s,N'%s',%s,%s,N'%s',N'%s',N'%s',N'%s',N'%s'," +
                        "N'%s',N'%s',N'%s',%s,N'%s',%s,N'%s',N'%s',N'%s',N'%s',N'%s',N'%s',N'%s',N'%s',%s,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)",
                        set(attrs[0],true),set(attrs[1],true),set(attrs[2],false),set(attrs[3],true),set(attrs[4],false),set(attrs[5],false),set(attrs[6],false),
                        set(attrs[7],false),set(attrs[8],false),set(attrs[9],false), set(attrs[10],false),set(attrs[11],false),set(attrs[12],true),set(attrs[13],true),
                        set(attrs[14],false),set(attrs[15],true),set(attrs[16],false),set(attrs[17],true),set(attrs[18],true),
                        set(attrs[19],false),set(attrs[20],false),set(attrs[21],false),set(attrs[22],false),set(attrs[23],false),set(attrs[24],false),
                        set(attrs[25],false),set(attrs[26],false),set(attrs[27],true),set(attrs[28],false),set(attrs[29],true),set(attrs[30],false),set(attrs[31],false)
                        ,set(attrs[32],false),set(attrs[33],false),set(attrs[34],false),set(attrs[35],false),set(attrs[36],false),set(attrs[37],false),set(attrs[38],true));
                stmt.executeUpdate(q2);

                stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            Toast.makeText(context,"ردیف جدید با موفقیت اضافه شد" , Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }

        String set(EditText e, boolean isInt){
            if (e.getText().toString().equals("") && isInt){
                return "NULL";
            }
            return e.getText().toString();
        }
    }
}