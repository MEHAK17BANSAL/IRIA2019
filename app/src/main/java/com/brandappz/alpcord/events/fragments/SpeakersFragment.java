package com.brandappz.alpcord.events.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.brandappz.alpcord.events.ConferenceApp;
import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.activities.MainActivity;
import com.brandappz.alpcord.events.adapter.SpeakersGridAdapter;
import com.brandappz.alpcord.events.model.Speaker;

import java.util.List;

public class SpeakersFragment extends Fragment {

	private ConferenceApp app;
	private MainActivity activity;
	private GridView grid;
	private List<Speaker> data;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.grid, container, false);

		return rootView;
	}
}
