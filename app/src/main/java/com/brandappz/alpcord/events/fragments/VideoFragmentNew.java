package com.brandappz.alpcord.events.fragments;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.activities.Config;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VideoFragmentNew.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VideoFragmentNew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragmentNew extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ProgressDialog pDialog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public VideoFragmentNew() {
        // Required empty public constructor
    }
    public GridView listView;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragmentNew.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragmentNew newInstance(String param1, String param2) {
        VideoFragmentNew fragment = new VideoFragmentNew();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_video_fragment_new, container, false);
        listView = (GridView)rootView.findViewById(R.id.fragment_grid);

        new AsyncHttpTask().execute("");
         return rootView;
    }

    public class AsyncHttpTask extends AsyncTask<String, String, String> {
        HttpResponse httpResponse;
        HttpClient httpclient;
        public HttpURLConnection httpURLConnection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(VideoFragmentNew.this.getActivity());
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... params) {

          /*  Integer result = 0;
            try {
                httpclient = new DefaultHttpClient();
                httpResponse = httpclient.execute(new HttpGet(params[0]));
                int statusCode = httpResponse.getStatusLine().getStatusCode();


            } catch (Exception e) {

                Log.d("getfucking", e.getLocalizedMessage());
            }*/

            try {
                URL url = new URL(Config.Video); //Enter URL here

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("accept-charset", "UTF-8");
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setConnectTimeout(10000);

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

                    if (httpURLConnection.getResponseCode() == 500) {
                    } else if (httpURLConnection.getResponseCode() == 80) {
                    } else {

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

    String streamToString(InputStream stream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line;
        String result = "";

        while ((line = bufferedReader.readLine()) != null) {

            result += line;
        }


        if (null != stream) {

            stream.close();
        }

        return result;
    }

    ArrayList<Beanclass> beanlist = new ArrayList<Beanclass>();

    private void parseResult(String result) {

        try {

            JSONArray posts = new JSONArray(result);
            Log.i("Image", "value======" + posts);
            //  String[] image1 = new String[posts.length()];
            // Constants galleryItem ;
            for (int i = 0; i < posts.length(); i++) {

                // galleryItem= new Constants();
                JSONObject post = posts.optJSONObject(i);
                //galleryItem.IMAGES(post.getString("full_path"));
                // String path = post.getString("full_path");

                Beanclass data = new Beanclass();
                data.setImageurl(post.optString("full_path"));
                beanlist.add(data);
                //String path = post.optString("full_path");
                //String newText= path.replace("\\", "/");
                // Log.e("imageURL", String.valueOf(newText));
                //Constants.IMAGES = image1[newText]/*="http:/*//***//*"+newText*/;
                //Constants.IMAGES =  new String[Integer.parseInt(image)];/*{*//*"http:*//**//*"*//*newText}*/;
                // Constants.IMAGES;
                //  Log.e("imageURL", image);
               /* galleryItem=new Constants();
                galleryItem.setImage(image);
                imageURL.add(galleryItem);*/

            }

            Log.e("gettingtag 2", "gttttttttttttttttttt");
          /*  ImageAdapter adap = new ImageAdapter(getActivity(), beanlist);
            listView.setAdapter(adap);
            adap.notifyDataSetChanged();*/
            SearchScreenUserPhotoAdapter adap = new SearchScreenUserPhotoAdapter(getActivity(), beanlist,getFragmentManager());
            listView.setAdapter(adap);
            adap.notifyDataSetChanged();
        } catch (JSONException e) {
            Toast.makeText(getActivity(), "hottttt", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
