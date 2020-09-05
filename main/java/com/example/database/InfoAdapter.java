package com.example.database;

import android.content.Context;
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
            holder.phone = convertView.findViewById(R.id.phone);
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


    private class ViewHolder {
        public TextView phone;
        public TextView region;
        public TextView isStar;
        public TextView star;
        public TextView description;
        public TextView have;
        public TextView notHave;

        public void fill(Info info){
            phone.setText(info.getPhone());
            region.setText(info.getRegion());
            isStar.setText(info.is_star());
            star.setText(info.getStar());
            description.setText(info.getDescription());
            have.setText(info.getHave());
            notHave.setText(info.getNotHave());

        }
    }
}
