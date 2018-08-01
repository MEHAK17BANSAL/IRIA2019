package com.brandappz.alpcord.events.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brandappz.alpcord.events.R;

public class OrganizingCommitteeFragment extends Fragment {

    private TextView hotelDescription1;
    private TextView hotelDescription2;
    private TextView hotelDescription3;
    private RelativeLayout i1, i2, i3, i4, i5, i6;
    private TextView h1, h2, h3, h4, h5, h6;
    private TextView t1, t2, t3, t4, t5, t6,text;
    private View line1, line2, line3, line4, line5, line6;
    private ImageView img1, img2, img3, img4, img5, img6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_organizingcommittee, container,
                false);
        return rootView;
    }

}
