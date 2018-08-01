package com.brandappz.alpcord.events.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandappz.alpcord.events.ConferenceApp;
import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.model.Sponsor;

public class SponsorsListAdapter extends ArrayAdapter<Sponsor> {

	private Context context;
	private int layoutId;
	private List<Sponsor> data;
	private ConferenceApp app;

	public SponsorsListAdapter(Context context, List<Sponsor> data) {
		super(context, R.layout.item_sponsor, data);
		this.context = context;
		this.data = data;
		this.layoutId = R.layout.item_sponsor;
		this.app = (ConferenceApp) ((Activity) context).getApplication();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;
		final Sponsor sponsor = data.get(position);

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutId, parent, false);

			holder = new ViewHolder();
			holder.tvName = (TextView) row.findViewById(R.id.tv_name);
			holder.ivPhoto = (ImageView) row.findViewById(R.id.iv_photo);
			holder.tvDescription = (TextView) row.findViewById(R.id.tv_description);

			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		app.getImagesManager().loadImage(sponsor.getPhoto().getFilename(), holder.ivPhoto);
		holder.tvName.setText(sponsor.getName());
		holder.tvDescription.setText(sponsor.getDescription());

		return row;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	private class ViewHolder {
		TextView tvName;
		ImageView ivPhoto;
		TextView tvDescription;
	}

}
