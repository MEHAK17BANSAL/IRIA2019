package com.brandappz.dhfl.event.fragments;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.brandappz.dhfl.event.ConferenceApp;
import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.activities.MainActivity;
import com.brandappz.dhfl.event.adapter.SupportListAdapter;
import com.brandappz.dhfl.event.model.Support;

import java.util.List;
import java.util.Vector;


public class SupportFragment extends Fragment {


    Vector<Support> support= new Vector<Support>();
    private MainActivity activity;
    private ConferenceApp app;
    private ListView list1;
    private List<Support> data;
    private SupportListAdapter adapter;

    public SupportFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_support, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.tv_title15);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView textView1 = (TextView) rootView.findViewById(R.id.tv_title13);
        textView1.setPaintFlags(textView1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView textView2 = (TextView) rootView.findViewById(R.id.tv_title11);
        textView2.setPaintFlags(textView2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView textView3 = (TextView) rootView.findViewById(R.id.tv_title14);
        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_title12);
        TextView textView5 = (TextView) rootView.findViewById(R.id.tv_title10);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                String[] s = {"sachin.kasralkar@dhfl.com"};
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, s);
                startActivity(emailIntent);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_DIAL);
                emailIntent.setData(Uri.parse("tel:+91 9167650598"));
                //String[] s = {"sachin.kasralkar@dhfl.com"};
                //emailIntent.setType("text/plain");
               // emailIntent.putExtra(Intent.EXTRA_EMAIL, s);
                startActivity(emailIntent);
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_DIAL);
                emailIntent.setData(Uri.parse("tel:+91 9820623620"));
                //String[] s = {"sachin.kasralkar@dhfl.com"};
                //emailIntent.setType("text/plain");
                // emailIntent.putExtra(Intent.EXTRA_EMAIL, s);
                startActivity(emailIntent);
            }
        });
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_DIAL);
                emailIntent.setData(Uri.parse("tel:+91 9619476977"));
                //String[] s = {"sachin.kasralkar@dhfl.com"};
                //emailIntent.setType("text/plain");
                // emailIntent.putExtra(Intent.EXTRA_EMAIL, s);
                startActivity(emailIntent);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                String[] s = {"samir.wajage@dhfl.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, s);
                emailIntent.setType("text/plain");
                startActivity(emailIntent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                String[] s = {"avinash.waghchaure@dhfl.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, s);
                emailIntent.setType("text/plain");
                startActivity(emailIntent);
            }
        });
        /*activity = (MainActivity) getActivity();
        app = (ConferenceApp) activity.getApplication();*/

       /* list1 = (ListView) rootView.findViewById(R.id.list1);
        support.add(new Support("Travel","Mr. Avinash Waghchaure","M No. : +91 9619476977","Email: avinash.waghchaure@dhfl.com"));
        adapter = new SupportListAdapter(getActivity(), data, support);
        list1.setAdapter(adapter);*/
       /* if (activity.isDualPane() && !data.isEmpty())
            showDetails(data.get(0).getId());*/
        return rootView;
    }

   /* // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

  /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

   /* @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

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
