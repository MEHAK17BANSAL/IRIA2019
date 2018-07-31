package com.brandappz.dhfl.event.activities;

/**
 * Created by perveen akhter on 23-03-2018.
 */

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.fragments.GalleryFragment;
import com.brandappz.dhfl.event.fragments.VideoFragment;

public class NewGalary extends Fragment implements View.OnClickListener{
    private RelativeLayout i1, i2, i3, i4, i5, i6;
    private TextView h1, h2, h3, h4, h5, h6;
    private TextView t1, t2, t3, t4, t5, t6,text;
    private View line1, line2, line3, line4, line5, line6;
    private ImageView img1, img2, img3, img4, img5, img6;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View rootView = inflater.inflate(R.layout.newgalary, container, false);
        text=(TextView)rootView.findViewById(R.id.text);
        img1 = (ImageView) rootView.findViewById(R.id.img1);
        img2 = (ImageView) rootView.findViewById(R.id.img2);


        line1 = (View) rootView.findViewById(R.id.line1);
        line2 = (View) rootView.findViewById(R.id.line2);

        i1 = (RelativeLayout) rootView.findViewById(R.id.i1);
        i2 = (RelativeLayout) rootView.findViewById(R.id.i2);

        h1 = (TextView) rootView.findViewById(R.id.h1);
        h2 = (TextView) rootView.findViewById(R.id.h2);



        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new GalleryFragment())
                .commit();

        i1.setOnClickListener(NewGalary.this);
        i2.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.i1) {

            i1.setBackgroundResource(R.drawable.dd4);
            i2.setBackgroundResource(R.drawable.d2);

            h1.setTextColor(getResources().getColor(android.R.color.white));
            h2.setTextColor(getResources().getColor(R.color.npurple));

            line1.setBackgroundColor(getResources().getColor(R.color.norange));
            line2.setBackgroundColor(getResources().getColor(R.color.norange));

            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.GONE);

            ((MainActivity)getActivity()).cpos(4);

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new GalleryFragment())
                    .commit();

        } else if (v.getId() == R.id.i2) {

            i1.setBackgroundResource(R.drawable.d4);
            i2.setBackgroundResource(R.drawable.dd2);

            h1.setTextColor(getResources().getColor(R.color.norange));
            h2.setTextColor(getResources().getColor(R.color.white));

            line1.setBackgroundColor(getResources().getColor(R.color.npurple));
            line2.setBackgroundColor(getResources().getColor(R.color.npurple));

            img1.setVisibility(View.GONE);
            img2.setVisibility(View.VISIBLE);

            ((MainActivity)getActivity()).cpos(5);

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new VideoFragment())
                    .commit();
        }

    }
}
