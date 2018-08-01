package com.brandappz.alpcord.events.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brandappz.alpcord.events.R;
import com.google.android.gcm.GCMRegistrar;


public class AboutConferenceFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_about, container, false);
		String regId = GCMRegistrar.getRegistrationId(getActivity().getApplicationContext());
		Toast.makeText(getActivity().getApplicationContext(),
				"regId: " + regId, Toast.LENGTH_LONG).show();

		return rootView;
	}

}
