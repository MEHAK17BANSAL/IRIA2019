package com.brandappz.dhfl.event.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.activities.Config;
import com.brandappz.dhfl.event.adapter.VideoListAdapter;
import com.brandappz.dhfl.event.model.Video;
import com.brandappz.dhfl.event.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class VideoFragment extends Fragment {

    ArrayList<Video> beanlist = new ArrayList<Video>();
    VideoListAdapter adap;
    private ProgressDialog pDialog;
    private ListView listView;

    public VideoFragment() {
        // Required empty public constructor
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        listView = (ListView) rootView.findViewById(R.id.video_list);


        adap = new VideoListAdapter(getActivity(), beanlist, getFragmentManager());
        listView.setAdapter(adap);
        if (beanlist.size() > 0) {

            adap.notifyDataSetChanged();
        }
        if (Utils.isOnline(getActivity())) {

            new AsyncHttpTask().execute("");
        } else {
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }


    private void parseResult(String result) {

        try {

            JSONArray posts = new JSONArray(result);

            for (int i = 0; i < posts.length(); i++) {

                JSONObject post = posts.optJSONObject(i);

                Video data = new Video();
                String string = null;
                data.setTitle(post.optString("full_path"));
                data.setThumbNailUrl(post.optString("thumbURL"));
                data.setContentData(post.optString("video_text"));
                beanlist.add(data);

            }

            if (beanlist.size() > 0) {

                adap.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }

    public class AsyncHttpTask extends AsyncTask<String, String, String> {
        public HttpURLConnection httpURLConnection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(VideoFragment.this.getActivity());
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... params) {


            try {
                URL url = new URL(Config.Video); //Enter URL here

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("accept-charset", "UTF-8");
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(params[0]);

                writer.flush();
                writer.close();
                httpURLConnection.connect();

                Log.e("rintdata", httpURLConnection.getInputStream().toString());
                return httpURLConnection.getInputStream().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();

                Log.e("yui", e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("ert", e.toString());
            }
            return null;
        }

        @Override

        protected void onPostExecute(String params) {

            try {
                if (200 <= httpURLConnection.getResponseCode() && httpURLConnection.getResponseCode() <= 299) {
                    pDialog.dismiss();
                    BufferedReader in = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }


                    parseResult(sb.toString());

                    Log.e("7677", sb.toString() + "-error code" + httpURLConnection.getResponseCode());
                    Log.e("jkl", in.toString() + "-error code" + httpURLConnection.getResponseCode());
                    in.close();

                } else {

                    BufferedReader in = new BufferedReader(new InputStreamReader((httpURLConnection.getErrorStream())));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }


                    Log.e("78ij678j", in.toString() + "-error code" + httpURLConnection.getResponseCode());
                    Log.e("56u7j", sb.toString() + "-error code" + httpURLConnection.getResponseCode());
                    in.close();


                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}
