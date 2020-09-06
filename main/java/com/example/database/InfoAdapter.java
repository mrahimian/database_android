package com.example.database;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InfoAdapter extends ArrayAdapter {
    private List<Info> info;

    public InfoAdapter(Context context, List<Info> info) {
        super(context, R.layout.row, info);
        this.info = info;
    }


    //    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Info contact = info.get(position);
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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.fill(contact);
        return convertView;
    }


    private class ViewHolder implements View.OnClickListener{
        public TextView id;
        public TextView phone;
        public TextView phone2;
        public TextView phone3;
        public TextView region;
        public TextView isStar;
        public TextView star;
        public TextView description;
        public TextView have;
        public TextView notHave;
        String p1 ;
        String p2 ;
        String p3 ;

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
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            String num = ((TextView)v).getText().toString();
            intent.setData(Uri.parse("tel:" + num));
            getContext().startActivity(intent);
        }
    }
}
