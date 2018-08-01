package com.brandappz.alpcord.events.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandappz.alpcord.events.ConferenceApp;
import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.constants.LectureTypes;
import com.brandappz.alpcord.events.model.Lecture;
import com.brandappz.alpcord.events.utils.Utils;

import java.util.List;

public class LecturesListAdapter extends ArrayAdapter<Lecture> {

	private Context context;
	private int layoutId;
	private List<Lecture> data;
	private ConferenceApp app;
	private boolean showDay;

	public LecturesListAdapter(Context context, List<Lecture> data, boolean showDay) {
		super(context, R.layout.item_lecture, data);
		this.context = context;
		this.data = data;
		this.layoutId = R.layout.item_lecture;
		this.showDay = showDay;
		this.app = (ConferenceApp) ((Activity) context).getApplication();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;
		final Lecture lecture = data.get(position);

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutId, parent, false);

			holder = new ViewHolder();
			holder.vType = row.findViewById(R.id.v_type);
			holder.tvTime = (TextView) row.findViewById(R.id.tv_time);
			holder.ivSpeaker = (ImageView) row.findViewById(R.id.iv_speaker);
			holder.tvTitle = (TextView) row.findViewById(R.id.tv_title);
			//holder.cbFavourite = (CheckBox) row.findViewById(R.id.cb_favourite);

			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		String time = Utils.getTime(lecture.getStartTime()) + " - " + Utils.getTime(lecture.getEndTime());
		if (showDay)
			time += " (" + Utils.getDay(lecture.getStartTime()) + ")";
		holder.tvTime.setText(time);
		holder.tvTitle.setText(lecture.getTitle());
//		holder.cbFavourite.setChecked(lecture.isFavourite());
		//app.getImagesManager().loadImage(lecture.getSpeaker().getPhoto().getFilename(), holder.ivSpeaker);
		switch (lecture.getType()) {
		case LectureTypes.LECTURE:
			holder.vType.setBackgroundResource(R.color.lecture);
			break;
		case LectureTypes.WORKSHOP:
			holder.vType.setBackgroundResource(R.color.workshop);
			break;
		case LectureTypes.DEBATE:
			holder.vType.setBackgroundResource(R.color.debate);
			break;
		case LectureTypes.OTHER:
			holder.vType.setBackgroundResource(R.color.other);
			break;
		}

		/*holder.cbFavourite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				lecture.setFavourite(!lecture.isFavourite());
				app.getDbManager().setLectureAsFavourite(lecture.getId(), lecture.isFavourite());
			}
		});*/

		return row;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public long getItemId(int position) {
		return data.get(position).getId();
	}

	private class ViewHolder {
		View vType;
		TextView tvTime;
		ImageView ivSpeaker;
		TextView tvTitle;
		CheckBox cbFavourite;
	}

}
