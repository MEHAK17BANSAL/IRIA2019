package com.brandappz.alpcord.events.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.brandappz.alpcord.events.ConferenceApp;
import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.activities.MainActivity;
import com.brandappz.alpcord.events.activities.details.LectureActivity;
import com.brandappz.alpcord.events.adapter.LecturesListAdapter;
import com.brandappz.alpcord.events.model.Lecture;
import com.brandappz.alpcord.events.model.Speaker;
import com.brandappz.framework.AppLog;

public class SpeakerDetailsFragment extends Fragment {

	public static final String SPEAKER_ID = "speaker_id";

	private ConferenceApp app;
	private Speaker speaker;
	private List<Lecture> lectures;
	private ImageView ivPhoto;
	private TextView tvName;
	private TextView tvCompany;
	private ListView list;
	private TextView tvDescription;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_speaker, container,
				false);
		app = (ConferenceApp) getActivity().getApplication();
		speaker = app.getDbManager().getSpeaker(
				getArguments().getLong(SPEAKER_ID));
		lectures = app.getDbManager().getLecturesForSpeaker(speaker);

		ivPhoto = (ImageView) rootView.findViewById(R.id.iv_speaker_photo);
		tvName = (TextView) rootView.findViewById(R.id.tv_speaker_name);
		tvCompany = (TextView) rootView.findViewById(R.id.tv_speaker_company);
		list = (ListView) rootView.findViewById(R.id.list);

		View header = inflater.inflate(R.layout.speaker_header, list, false);
		header.setClickable(false);
		tvDescription = (TextView) header.findViewById(R.id.tv_description);

		tvName.setText(speaker.getName() + " " + speaker.getSurname());
		tvCompany.setText(speaker.getCompany());
		tvDescription.setText(speaker.getDescription());
		tvDescription.setClickable(false);

		app.getImagesManager().loadImage(speaker.getPhoto().getFilename(),
				ivPhoto);
		list.addHeaderView(header);
		list.setAdapter(new LecturesListAdapter(getActivity(), lectures, true));
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppLog.logMessage("SpeakerDetailsFragment::onCreateView",
						"SpeakerDetailsFragment::onCreateView:setOnItemClickListener:id:"
								+ id);
				if (id != -1)
					showLecture(id);
			}
		});

		return rootView;
	}

	private void showLecture(long lectureId) {
		FragmentActivity activity = getActivity();
		Bundle args = new Bundle();
		args.putLong(LectureDetailsFragment.LECTURE_ID, lectureId);
		if (activity instanceof MainActivity) {
			Fragment fragment = new LectureDetailsFragment();
			fragment.setArguments(args);
			((MainActivity) activity).setDetails(fragment);
		} else {
			LectureActivity.startActivity(activity, args);
		}
	}

}
