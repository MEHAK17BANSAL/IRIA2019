package com.brandappz.alpcord.events.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.brandappz.alpcord.events.R;


/**
 * Created by perveen akhter on 29-03-2018.
 */

public class TabmolaFragment extends Fragment {
    public WebView mWebView;
    String user_id = "";
    ProgressDialog dialog;
    String url;
RelativeLayout one;
LinearLayout btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tambola, container, false);
       return v;
    }

}
