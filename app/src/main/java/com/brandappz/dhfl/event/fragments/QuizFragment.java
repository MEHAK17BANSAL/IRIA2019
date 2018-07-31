package com.brandappz.dhfl.event.fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.brandappz.dhfl.event.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {
    public WebView mWebView;


    public QuizFragment() {
        // Required empty public constructor
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("deprecation")
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_quiz, container, false);
        mWebView = (WebView) v.findViewById(R.id.webvu);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setInitialScale((int)(100*mWebView.getScale()));

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);



        mWebView.setInitialScale(1);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);

        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(false);
        //mWebView.zoomBy(0.1f);
        String user_id = "";
        SharedPreferences pref = getActivity().getSharedPreferences("DashBoard", 0);

        user_id = pref.getString("userId", user_id);
        Log.i("User_id","======"+user_id);


        mWebView.loadUrl("http://socialcampaign.co.in/apps/event_management/dhfl/quiz/verifyUser.php?brand_id=2&user_id="+user_id);
        Log.i("Quiz","Url===="+mWebView);
       // Toast.makeText(QuizFragment.this, String.format("User ID:%s", mWebView),Toast.LENGTH_LONG).show();
        mWebView.setWebViewClient(new WebViewClient() {

            @SuppressWarnings("deprecation")
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url){
                Log.e("urlllllllllll",url+"--"+mWebView.getUrl());
                dialog.dismiss();
                String webUrl = mWebView.getUrl();

               // Toast.makeText(getActivity(), ""+url, Toast.LENGTH_SHORT).show();
            }


        });


        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        // Inflate the layout for this fragment
        return v;
    }




}
