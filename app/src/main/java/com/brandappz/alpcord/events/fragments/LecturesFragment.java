package com.brandappz.alpcord.events.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.brandappz.alpcord.events.ConferenceApp;
import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.activities.MainActivity;
import com.brandappz.alpcord.events.adapter.LecturesListAdapter;
import com.brandappz.alpcord.events.model.Lecture;
import com.brandappz.alpcord.events.utils.Utils;

import java.util.List;

public class LecturesFragment extends Fragment {

	public static final String DAY_TAG = "day_tag";

	private ConferenceApp app;
	private MainActivity activity;
	private ListView list;
	private List<Lecture> data;
	private LecturesListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list, container, false);
		activity = (MainActivity) getActivity();
		app = (ConferenceApp) activity.getApplication();

		list = (ListView) rootView.findViewById(R.id.list);
		data = app.getDbManager().getLecturesForDay(Utils.stringToDate(getArguments().getString(DAY_TAG)));
		adapter = new LecturesListAdapter(getActivity(), data, false);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
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

	/*private void showDetails(long lectureId) {
		Bundle args = new Bundle();
		args.putLong(LectureDetailsFragment.LECTURE_ID, lectureId);
		if (activity.isDualPane()) {
			//Fragment fragment = new LectureDetailsFragment();
			//fragment.setArguments(args);
			//activity.setDetails(fragment);
		} else {
			LectureActivity.startActivity(activity, args);
		}
	}*/

}
