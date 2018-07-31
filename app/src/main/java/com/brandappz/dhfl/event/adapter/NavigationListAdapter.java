package com.brandappz.dhfl.event.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.exceptions.InconsistentNavigationException;

public class NavigationListAdapter extends ArrayAdapter<String> {

	private Context context;
	private int layoutId;
	private String[] titles;
	private int[] icons;

	public NavigationListAdapter(Context context, String[] titles) {
		super(context, R.layout.item_navigation);
		this.layoutId = R.layout.item_navigation;
		this.context = context;
		this.titles = titles;
		this.icons = new int[] { R.drawable.menu_agenda_pressed,
				R.drawable.menu_speakers_pressed, R.drawable.menu_about_pressed, R.drawable.menu_hotel_pressed, R.drawable.menu_qr_pressed,  R.drawable.video_icon,  R.drawable.quiz_icon, R.drawable.leaderboard_icon,  R.drawable.supoort_icon,  /* R.drawable.menu_location_pressed*/};
//		this.icons = new int[] { R.drawable.menu_agenda_pressed,
//				R.drawable.menu_plan_pressed, R.drawable.menu_speakers_pressed, R.drawable.menu_location_pressed, R.drawable.menu_sponsors_pressed, 
//				R.drawable.menu_qr_pressed, R.drawable.menu_about_pressed };
		if (this.titles.length != this.icons.length)
			throw new InconsistentNavigationException();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutId, parent, false);

			holder = new ViewHolder();
			holder.tvTitle = (TextView) row.findViewById(R.id.tv_title);

			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		holder.tvTitle.setText(titles[position]);
		holder.tvTitle.setCompoundDrawablesWithIntrinsicBounds(icons[position], 0, 0, 0);

		return row;
	}

	@Override
	public int getCount() {
		return titles.length;
	}

	private class ViewHolder {
		TextView tvTitle;
	}

}
