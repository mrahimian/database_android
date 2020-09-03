package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    void connectToDB(){
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new Runnable() {
            @Override
            public void run() {
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
                    String queryStmt = "select * from dbo.MidStore";

//                    PreparedStatement preparedStatement = conn
//                            .prepareStatement(queryStmt);
                    Statement stmt = conn.createStatement();


//                    preparedStatement.executeUpdate();
//                    ResultSet resultSet = preparedStatement.executeQuery();
                    ResultSet rslt = stmt.executeQuery(queryStmt);

                    while(rslt.next())
                    {
                        int id = rslt.getInt(1);
                        int rno = rslt.getInt(4);
                        String name = rslt.getString(2);
                        String city = rslt.getString(3);
                        Log.e(id +"<br>" +" " +name + " "+"<br>" + city +"<br>" + rno + "<br>","msg");
                    }

                    stmt.close();
                    conn.close();
                    rslt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                } catch (ClassNotFoundException e) {
                    Log.e("ERRO", e.getMessage());
                } catch (Exception e) {
                    Log.e("ERRO", e.getMessage());
                }

            }
        });
    }


}