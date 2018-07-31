package com.brandappz.dhfl.event.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.brandappz.dhfl.event.ConferenceApp;
import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.activities.MainActivity;
import com.brandappz.dhfl.event.activities.details.LectureActivity;
import com.brandappz.dhfl.event.adapter.LecturesListAdapter;
import com.brandappz.dhfl.event.model.Lecture;

public class MyPlanFragment extends Fragment {

	private ConferenceApp app;
	private MainActivity activity;
	private ListView list;
	private List<Lecture> data;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list, container, false);
		activity = (MainActivity) getActivity();
		app = (ConferenceApp) activity.getApplication();

		list = (ListView) rootView.findViewById(R.id.list);
		data = app.getDbManager().getFavouriteLectures();
		list.setAdapter(new LecturesListAdapter(getActivity(), data, true));
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				showDetails(id);
			}
		});
		/*if (activity.isDualPane() && !data.isEmpty())
			showDetails(data.get(0).getId());
*/
		return rootView;
	}

	private void showDetails(long lectureId) {
		Bundle args = new Bundle();
		args.putLong(LectureDetailsFragment.LECTURE_ID, lectureId);
//		if (activity.isDualPane()) {
//			Fragment fragment = new LectureDetailsFragment();
//			fragment.setArguments(args);
//			activity.setDetails(fragment);
//		} else {
//			LectureActivity.startActivity(activity, args);
//		}
	}

}
