package com.brandappz.alpcord.events.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.model.Adapter;
import com.brandappz.alpcord.events.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perveen akhter on 23-03-2018.
 */

public class Flightframent extends Fragment {
   RecyclerView list;
   RecyclerView.LayoutManager layoutManager;
   List<Item> items = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.flightticket, container, false);
        list=(rootView).findViewById(R.id.recycler);
        list.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);
        setData();
        return rootView;

    }
private void setData(){
        for(int i=0;i<20;i++){
            if(i% 2==0){
                Item item=new Item("this is item"+(i+1),"this is child item"+(i+1),true);
                items.add(item);
                }
            Adapter adapter = new Adapter(items);
            list.setAdapter(adapter);
        }
}

}







