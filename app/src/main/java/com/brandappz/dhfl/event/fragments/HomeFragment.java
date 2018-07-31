package com.brandappz.dhfl.event.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brandappz.dhfl.event.ConferenceApp;
import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.activities.MainActivity;
import com.brandappz.dhfl.event.model.DayTag;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    RecyclerView recyclerView;
    Agendaadapter adapter;
    private ConferenceApp app;
    private ViewPager pager;
    private List<DayTag> data;
    private RelativeLayout i1, i2, i3, i4, i5, i6;
    private TextView h1, h2, h3, h4, h5, h6;
    private TextView t1, t2, t3, t4, t5, t6;
    private View line1, line2, line3, line4, line5, line6,line66;
    private ImageView img1, img2, img3, img4, img5, img6;
    private List<AgandaBean> agendabean = new ArrayList<AgandaBean>();

    View v1,v2,v3,v4;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.homefragment, container, false);
        app = (ConferenceApp) getActivity().getApplication();

        //pager = (ViewPager) rootView.findViewById(R.id.pager);
        data = app.getDbManager().getDayTags();

        return rootView;

    }
}


