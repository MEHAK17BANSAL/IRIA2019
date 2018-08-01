package com.brandappz.alpcord.events.fragments;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brandappz.alpcord.events.R;

import java.util.List;

/**
 * Created by perveen akhter on 22-03-2018.
 */

public class Agendaadapter extends RecyclerView.Adapter<Agendaadapter.MyViewHolder>{
    private List<AgandaBean> agendabean;
    public Agendaadapter(List<AgandaBean> agendabean) {
        this.agendabean=agendabean;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
public TextView title,subtitle,desc1,desc2;
        public MyViewHolder(View view) {
            super(view);
            title=(TextView)view.findViewById(R.id.title);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.agendaitem, parent, false);




        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(Html.fromHtml(agendabean.get(position).getTitle()));


    }

    @Override
    public int getItemCount() {
        return agendabean.size();
    }
}
