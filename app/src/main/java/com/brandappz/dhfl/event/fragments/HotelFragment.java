package com.brandappz.dhfl.event.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brandappz.dhfl.event.ConferenceApp;
import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.activities.MainActivity;
import com.brandappz.dhfl.event.activities.details.LocationsActivity;
import com.brandappz.dhfl.event.model.Place;
import com.brandappz.framework.AppLog;


public class HotelFragment extends Fragment {

    private ConferenceApp app;
    private Place place, place1;
    private TextView placeAddress, text1, text2, text3, text4, text5, text6, text7;
    private TextView placeName;
    private LinearLayout call1, call2,map2,map1;
    private ImageButton ibPlaceMap, ibPlaceMap1;
    private ImageView two1, two2;

    public void sendm(String to) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

/* Fill it with Data */
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{to});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");

/* Send it off to the Activity-Chooser */
        getActivity().startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hotel, container, false);
        app = (ConferenceApp) getActivity().getApplication();
        two1 = (ImageView) rootView.findViewById(R.id.two1);
        two2 = (ImageView) rootView.findViewById(R.id.two2);
        call1 = (LinearLayout) rootView.findViewById(R.id.call1);
        call2 = (LinearLayout) rootView.findViewById(R.id.call2);
        map2= (LinearLayout) rootView.findViewById(R.id.map2);
        map1= (LinearLayout) rootView.findViewById(R.id.map1);

        map1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUri = "http://maps.google.com/maps?q=loc:" + "13.7055" + "," + "100.4907" + " (" + "AVANI Riverside Bangkok Hotel" + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                getActivity().startActivity(intent);
            }
        });

        map2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUri = "http://maps.google.com/maps?q=loc:" + "13.7047" + "," + "100.4917" + " (" + "Anantara Riverside Bangkok Resort" + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                getActivity().startActivity(intent);
            }
        });

        two2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendm("");
            }
        });
        two1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendm("");

            }
        });
        call1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                call("+66 2 431 9100");
            }
        });
        call2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                call("+66 2 476 0022");

            }
        });
        return rootView;
    }

    public void call(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    private void showPlace1() {
        FragmentActivity activity = getActivity();
        Bundle args = new Bundle();
        //args.putLong(LocationsFragment.PLACE_ID);
        //AppLog.logMessage("HotelFragment::showPlace", "HotelFragment::showPlace:placeId:"+);
        if (activity instanceof MainActivity) {
            Fragment fragment = new LocationsFragment();
            fragment.setArguments(args);
            AppLog.logMessage("HotelFragment::showPlace", "HotelFragment::showPlace:Thru Main");
            ((MainActivity) activity).setContent(fragment);
        } else {
            AppLog.logMessage("HotelFragment::showPlace", "HotelFragment::showPlace:Thru Location");
            LocationsActivity.startActivity(activity, args);
        }
    }

    private void showPlace() {
        FragmentActivity activity = getActivity();
        Bundle args = new Bundle();
        //args.putLong(LocationsFragment.PLACE_ID, placeId);
        //AppLog.logMessage("HotelFragment::showPlace", "HotelFragment::showPlace:placeId:"+placeId);
        if (activity instanceof MainActivity) {
            Fragment fragment = new LocationsFragmentOld();
            fragment.setArguments(args);
            AppLog.logMessage("HotelFragment::showPlace", "HotelFragment::showPlace:Thru Main");
            ((MainActivity) activity).setContent(fragment);
        } else {
            AppLog.logMessage("HotelFragment::showPlace", "HotelFragment::showPlace:Thru Location");
            LocationsActivity.startActivity(activity, args);
        }
    }


}
