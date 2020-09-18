package com.example.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InfoAdapter extends ArrayAdapter {
    List<Info> info;

    public InfoAdapter(Context context, List<Info> info) {
        super(context, R.layout.row, info);
        this.info = info;
    }


    //    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Info i = info.get(position);
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row, parent, false);
            holder = new ViewHolder();
            holder.id = convertView.findViewById(R.id.id);
            holder.phone = convertView.findViewById(R.id.phone);
            holder.phone2 = convertView.findViewById(R.id.phone2);
            holder.phone3 = convertView.findViewById(R.id.phone3);
            holder.region = convertView.findViewById(R.id.region);
            holder.isStar = convertView.findViewById(R.id.is_star);
            holder.star = convertView.findViewById(R.id.star);
            holder.description = convertView.findViewById(R.id.description);
            holder.have = convertView.findViewById(R.id.have);
            holder.notHave = convertView.findViewById(R.id.not_have);
            holder.e1 = convertView.findViewById(R.id.edit1);
            holder.e2 = convertView.findViewById(R.id.edit2);
            holder.e3 = convertView.findViewById(R.id.edit3);
            holder.e4 = convertView.findViewById(R.id.edit4);
            holder.e5 = convertView.findViewById(R.id.edit5);
            holder.edit = convertView.findViewById(R.id.edit);
            holder.fullEdit = convertView.findViewById(R.id.full_edit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (i.is_star().equals("2")){
            convertView.setBackgroundColor(Color.rgb(0,255,255));
        }
        else {
            if (i.isExist()){
                convertView.setBackgroundColor(Color.rgb(51,204,166));
            }
            else {
                convertView.setBackgroundColor(Color.WHITE);
            }
        }

        holder.fill(i);

        return convertView;
    }


    private class ViewHolder implements View.OnClickListener{

        public TextView id;
        public TextView phone;
        public TextView phone2;
        public TextView phone3;
        public TextView region;
        public EditText isStar;
        public EditText star;
        public EditText description;
        public EditText have;
        public EditText notHave;
        public ImageButton e1;
        public ImageButton e2;
        public ImageButton e3;
        public ImageButton e4;
        public ImageButton e5;
        public Button edit;
        public Button fullEdit;
        String score ;
        String scoreDes ;
        String des ;
        String exist ;
        String notExist ;


        public void fill(Info info){
            id.setText(info.getId());
            phone.setText(info.getPhone());
            phone2.setText(info.getPhone2());
            phone3.setText(info.getPhone3());
            phone.setOnClickListener(this);
            phone2.setOnClickListener(this);
            phone3.setOnClickListener(this);
            region.setText(info.getRegion());
            isStar.setText(info.is_star());
            star.setText(info.getStar());
            description.setText(info.getDescription());
            have.setText(info.getHave());
            notHave.setText(info.getNotHave());
            edit.setOnClickListener(this);
            fullEdit.setOnClickListener(this);
            e1.setOnClickListener(this);
            e2.setOnClickListener(this);
            e3.setOnClickListener(this);
            e4.setOnClickListener(this);
            e5.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.edit1:
                    isStar.setEnabled(true);
                    break;
                case R.id.edit2:
                    star.setEnabled(true);
                    break;
                case R.id.edit3:
                    description.setEnabled(true);
                    break;
                case R.id.edit4:
                    have.setEnabled(true);
                    break;
                case R.id.edit5:
                    notHave.setEnabled(true);
                    break;
                case R.id.edit:
                    View parentRow = (View) v.getParent().getParent();
                    ListView listView = (ListView) parentRow.getParent();
                    final int position = listView.getPositionForView(parentRow);
                    Edit edit = new Edit(v.getContext(),info.get(position),getParams());
                    edit.execute();
                    break;
                case R.id.full_edit:
                    Intent in = new Intent(getContext(),FullEdit.class);
                    in.putExtra("id",id.getText());
                    getContext().startActivity(in);
                    break;

                default:
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    String num = ((TextView)v).getText().toString();
                    intent.setData(Uri.parse("tel:" + num));
                    getContext().startActivity(intent);
            }

        }
        String[] getParams(){
            score = isStar.getText().toString();
            scoreDes = star.getText().toString();
            des = description.getText().toString();
            exist = have.getText().toString();
            notExist = notHave.getText().toString();
            return new String[]{score,scoreDes,des,exist,notExist};
        }
    }

    private class Edit extends AsyncTask<String, Void, String> {

        ProgressDialog pd;
        Context context;
        Info former;
        String[] current;

        public Edit(Context context, Info former, String[] current) {
            this.context = context;
            this.former = former;
            this.current = current;
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(context);
            pd.setMessage("در حال ویرایش اطلاعات ...");
            pd.setCancelable(false);
            pd.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String _user = "sa";
            String _pass = "----";
            String _DB = "----";
            String _server = "----";

            Connection conn = null;
            String ConnURL = null;
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                ConnURL = "jdbc:jtds:sqlserver://" + _server + ";"
                        + "databaseName=" + _DB + ";user=" + _user + ";password="
                        + _pass + ";";
                conn = DriverManager.getConnection(ConnURL);

                Statement stmt = conn.createStatement();

                String q2 = "update dbo.MidStore set isStared = '" + current[0] + "' , StarDescribtion = N'" +current[1] + "' " +
                        ", Describtion = N'" + current[2] +  "' , Exist = N'" + current[3] + "' , NotExist = N'" + current[4] + "' " +
                        "where id = '" + former.getId() + "'";
                stmt.executeUpdate(q2);

                String edition = userEditionQuery();
                stmt.executeUpdate("insert into dbo.users values (N'" + edition + "')");


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
            Toast.makeText(context,"Edit Has Successfully Done" , Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }

        String userEditionQuery(){
            String query = LoginActivity.user + "\n";
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            query += dateFormat.format(date) + "\n";
            query += "کد فروشگاه : " + former.getId() + "\n";
            if (!current[0].trim().equals(former.is_star().trim())){
                query += ("ستاره 1 : " + former.is_star() + " ستاره 2 : " + current[0] + "\n");
            }
            if (!current[1].trim().equals(former.getStar().trim())){
                query += ("توضیحات ستاره 1 : " + former.getStar() + "\n" + " توضیحات ستاره 2 : " + current[1] + "\n");
            }
            if (!current[2].trim().equals(former.getDescription().trim())){
                query += ("توضیحات 1 : " + former.getDescription() + "\n" + " توضیحات 2 : " + current[2] + "\n");
            }
            if (!current[3].trim().equals(former.getHave().trim())){
                query += ("دارد 1 : " + former.getHave() + " دارد 2 : " + "\n" + current[3] + "\n");
            }
            if (!current[4].trim().equals(former.getNotHave().trim())){
                query += ("ندارد 1 : " + former.getNotHave() + " ندارد 2 : " + "\n" + current[4] + "\n");
            }
            return query;
        }
    }
}
