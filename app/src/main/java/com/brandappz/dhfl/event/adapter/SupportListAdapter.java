package com.brandappz.dhfl.event.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandappz.dhfl.event.ConferenceApp;
import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.model.Support;

import java.util.List;
import java.util.Vector;

/**
 * Created by Ankit on 24-03-2017.
 */

public class SupportListAdapter extends ArrayAdapter<Support> {

    private Context context;
    private int layoutId;
    private List<Support> data;
    private ConferenceApp app;
    private boolean showDay;

    public SupportListAdapter(Context context, List<Support> data, Vector<Support> showDay) {
        super(context, R.layout.item_support, data);
        this.context = context;
        this.data = data;
        this.layoutId = R.layout.item_support;

        this.app = (ConferenceApp) ((Activity) context).getApplication();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutId, parent, false);

            holder = new ViewHolder();
            holder.vType = (TextView) row.findViewById(R.id.tv_time1);
            holder.tvTime = (TextView) row.findViewById(R.id.tv_title1);
            holder.ivSpeaker = (TextView) row.findViewById(R.id.tv_title2);
            holder.tvTitle = (TextView) row.findViewById(R.id.tv_title3);
            //holder.cbFavourite = (CheckBox) row.findViewById(R.id.cb_favourite);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        Support mrb = data.get(position);
        System.out.println("Position=" + position);

        holder.vType.setText(mrb.gtumcaFirstName);
        holder.tvTime.setText(mrb.gtumcaLastName);
        holder.ivSpeaker.setText(mrb.gtumcaBirthdate);
        holder.tvTitle.setText(mrb.getGtumcaEmail);
        holder.gtumcaIvIcon.setImageResource(R.drawable.round_logo);
        return row;
    }

    static class MyStringReaderHolder {
        TextView gtumcaTvFirstName, gtumcaTvLastName, gtumcaTvBirthDate, gtumcaTvEmail;
        ImageView gtumcaIvIcon;
    }


    class ViewHolder {
        TextView vType;
        TextView tvTime;
        TextView ivSpeaker;
        TextView tvTitle;
        ImageView gtumcaIvIcon;
        CheckBox cbFavourite;
    }
}



