package com.example.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
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


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (i.is_star().equals("2")){
            convertView.setBackgroundColor(Color.rgb(0,255,255));
        }
        else if (i.is_star().equals("1")){
            convertView.setBackgroundColor(Color.rgb(51,204,166));
        }
        else {
            convertView.setBackgroundColor(Color.WHITE);
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

                    break;

                default:
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    String num = ((TextView)v).getText().toString();
                    intent.setData(Uri.parse("tel:" + num));
                    getContext().startActivity(intent);
            }

        }
        void getParams(){
            score = isStar.getText().toString();
            scoreDes = star.getText().toString();
            des = description.getText().toString();
            exist = have.getText().toString();
            notExist = notHave.getText().toString();
        }
    }

    private class Edit extends AsyncTask<String, Void, String> {

        ProgressDialog pd;
        Context context;

        public Edit(Context context) { this.context = context; }
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

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
        }
    }
}
