package com.brandappz.alpcord.events.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.brandappz.alpcord.events.ConferenceApp;
import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.model.Speaker;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpeakersGridAdapter extends ArrayAdapter<Speaker> {

	private Context context;
	private int layoutId;
	private List<Speaker> data;
	private ConferenceApp app;

	public SpeakersGridAdapter(Context context, List<Speaker> data) {
		super(context, R.layout.item_speaker, data);
		this.context = context;
		this.data = data;
		this.layoutId = R.layout.item_speaker;
		this.app = (ConferenceApp) ((Activity) context).getApplication();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;
		final Speaker speaker = data.get(position);

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutId, parent, false);

			Log.i("Raw","data"+row);

			holder = new ViewHolder();
			holder.ivPhoto = (CircleImageView) row.findViewById(R.id.iv_photo);
			holder.tvName = (TextView) row.findViewById(R.id.tv_name);
			holder.tvCompany = (TextView) row.findViewById(R.id.tv_company);

			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

	//	Picasso.with(context).load(speaker.getPhoto().getFilename()).into(holder.ivPhoto);

		app.getImagesManager().loadImage(speaker.getPhoto().getFilename(), holder.ivPhoto);
		Log.i("Image ","shown======="+app);
		holder.tvName.setText(speaker.getName() + " " + speaker.getSurname());
		holder.tvCompany.setText(speaker.getCompany());

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
		CircleImageView ivPhoto;
		TextView tvName;
		TextView tvCompany;
	}

}
