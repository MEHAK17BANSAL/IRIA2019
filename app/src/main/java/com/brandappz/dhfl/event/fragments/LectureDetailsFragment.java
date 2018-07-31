package com.brandappz.dhfl.event.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brandappz.dhfl.event.ConferenceApp;
import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.activities.MainActivity;
import com.brandappz.dhfl.event.activities.details.LocationsActivity;
import com.brandappz.dhfl.event.activities.details.SpeakerActivity;
import com.brandappz.dhfl.event.constants.LectureTypes;
import com.brandappz.dhfl.event.model.Lecture;
import com.brandappz.dhfl.event.utils.Utils;
//import android.widget.RatingBar;
import com.brandappz.framework.AppLog;

public class LectureDetailsFragment extends Fragment {

	public static final String LECTURE_ID = "lecture_id";

	private ConferenceApp app;
	private Lecture lecture;
	private CheckBox cbFavourite;
	private TextView tvTitle;
	private TextView tvTime;
	private View vType;
	private TextView tvDescription;
	private ImageButton ibPlaceMap;
	private TextView tvPlaceName;
	private TextView tvPlaceAddress;
	private ImageView ivSpeakerPhoto;
	private TextView tvSpeakerName;
	private TextView tvSpeakerCompany;
	// private RatingBar ratingBar;
	// private TextView tvRating;
	private RelativeLayout rlSpeaker;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_lecture, container,
				false);
		app = (ConferenceApp) getActivity().getApplication();
		lecture = app.getDbManager().getLecture(
				getArguments().getLong(LECTURE_ID));

		cbFavourite = (CheckBox) rootView.findViewById(R.id.cb_favourite);
		tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
		tvTime = (TextView) rootView.findViewById(R.id.tv_time);
		vType = rootView.findViewById(R.id.v_type);
		tvDescription = (TextView) rootView.findViewById(R.id.tv_description);
		ibPlaceMap = (ImageButton) rootView.findViewById(R.id.ib_place_map);
		tvPlaceName = (TextView) rootView.findViewById(R.id.tv_place_name);
		tvPlaceAddress = (TextView) rootView
				.findViewById(R.id.tv_place_address);
		/*ivSpeakerPhoto = (ImageView) rootView
				.findViewById(R.id.iv_speaker_photo);*/
		tvSpeakerName = (TextView) rootView.findViewById(R.id.tv_speaker_name);
		tvSpeakerCompany = (TextView) rootView
				.findViewById(R.id.tv_speaker_company);
		// ratingBar = (RatingBar) rootView.findViewById(R.id.rating_bar);
		// tvRating = (TextView) rootView.findViewById(R.id.tv_rating);
		rlSpeaker = (RelativeLayout) rootView.findViewById(R.id.rl_speaker);
		if (lecture.getSpeaker().isDisplayOnScreen())
			rlSpeaker.setVisibility(View.VISIBLE);
		else
			rlSpeaker.setVisibility(View.INVISIBLE);

		String time = Utils.getTime(lecture.getStartTime()) + " - "
				+ Utils.getTime(lecture.getEndTime()) + " ("
				+ Utils.getDay(lecture.getStartTime()) + ")";
		cbFavourite.setChecked(lecture.isFavourite());
		tvTitle.setText(lecture.getTitle());
		tvTime.setText(time);
		tvDescription.setText(lecture.getDescription());
		tvPlaceName.setText(lecture.getPlace().getName());
		tvPlaceAddress.setText(String.format(lecture.getPlace().getAddress()));
		tvSpeakerName.setText(lecture.getSpeaker().getName() + " "
				+ lecture.getSpeaker().getSurname());
		tvSpeakerCompany.setText(lecture.getSpeaker().getCompany());
		if (lecture.getNote() == 0) {
			// ratingBar.setOnRatingBarChangeListener(new
			// OnRatingBarChangeListener() {
			// @Override
			// public void onRatingChanged(RatingBar ratingBar, float rating,
			// boolean fromUser) {
			// app.getDbManager().rateLecture(lecture.getId(), rating);
			// }
			// });
		} else {
			// ratingBar.setEnabled(false);
			// ratingBar.setRating(lecture.getNote());
			// tvRating.setText(String.format(getResources().getString(R.string.rating_text),
			// lecture.getNote()));
		}
	/*	app.getImagesManager().loadImage(
				lecture.getSpeaker().getPhoto().getFilename(), ivSpeakerPhoto);
	*/	switch (lecture.getType()) {
		case LectureTypes.LECTURE:
			vType.setBackgroundResource(R.color.lecture);
			break;
		case LectureTypes.WORKSHOP:
			vType.setBackgroundResource(R.color.workshop);
			break;
		case LectureTypes.DEBATE:
			vType.setBackgroundResource(R.color.debate);
			break;
		case LectureTypes.OTHER:
			vType.setBackgroundResource(R.color.other);
			break;
		}
		ibPlaceMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showPlace(lecture.getPlace().getId());
			}
		});
		cbFavourite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				lecture.setFavourite(!lecture.isFavourite());
				app.getDbManager().setLectureAsFavourite(lecture.getId(),
						lecture.isFavourite());
			}
		});
		rlSpeaker.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showSpeaker(lecture.getSpeaker().getId());
			}
		});

		return rootView;
	}

	private void showSpeaker(long speakerId) {
		FragmentActivity activity = getActivity();
		Bundle args = new Bundle();
		args.putLong(SpeakerDetailsFragment.SPEAKER_ID, speakerId);
		if (activity instanceof MainActivity) {
			Fragment fragment = new SpeakerDetailsFragment();
			fragment.setArguments(args);
			((MainActivity) activity).setDetails(fragment);
		} else {
			SpeakerActivity.startActivity(activity, args);
		}
	}

	private void showPlace(long placeId) {
		FragmentActivity activity = getActivity();
		Bundle args = new Bundle();
		args.putLong(LocationsFragment.PLACE_ID, placeId);
		if (activity instanceof MainActivity) {
			Fragment fragment = new LocationsFragment();
			fragment.setArguments(args);
			((MainActivity) activity).setDetails(fragment);
		} else {
			LocationsActivity.startActivity(activity, args);
		}
	}

}
