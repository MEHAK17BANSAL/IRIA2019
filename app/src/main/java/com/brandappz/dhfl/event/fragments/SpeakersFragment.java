package com.brandappz.dhfl.event.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.brandappz.dhfl.event.ConferenceApp;
import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.activities.MainActivity;
import com.brandappz.dhfl.event.activities.details.SpeakerActivity;
import com.brandappz.dhfl.event.adapter.SpeakersGridAdapter;
import com.brandappz.dhfl.event.model.Speaker;

import java.util.List;

public class SpeakersFragment extends Fragment {

	private ConferenceApp app;
	private MainActivity activity;
	private GridView grid;
	private List<Speaker> data;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.grid, container, false);
		activity = (MainActivity) getActivity();
		app = (ConferenceApp) activity.getApplication();

		grid = (GridView) rootView.findViewById(R.id.grid);
		data = app.getDbManager().getAllSpeakers();
		grid.setAdapter(new SpeakersGridAdapter(getActivity(), data));
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//showDetails(id);
			}
		});
		/*if (activity.isDualPane() && !data.isEmpty())
			showDetails(data.get(0).getId());
*/
		return rootView;
	}

	private void showDetails(long speakerId) {
		Bundle args = new Bundle();
		args.putLong(SpeakerDetailsFragment.SPEAKER_ID, speakerId);
		/*if (activity.isDualPane()) {
			Fragment fragment = new SpeakerDetailsFragment();
			fragment.setArguments(args);
			activity.setDetails(fragment);
		} else {
			SpeakerActivity.startActivity(activity, args);
		}*/
	}
}
