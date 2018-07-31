package com.brandappz.dhfl.event.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandappz.dhfl.event.ConferenceApp;
import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.twitter.TwitterTweet;

public class TwitterTweetAdapter extends ArrayAdapter<TwitterTweet> {

	private ConferenceApp app;
	private Context context;
	private List<TwitterTweet> data;
	private int layoutId;

	public TwitterTweetAdapter(Context context, List<TwitterTweet> data) {
		super(context, R.layout.item_twitter_tweet, data);
		this.context = context;
		this.data = data;
		this.layoutId = R.layout.item_twitter_tweet;
		//this.app = (ConferenceApp) ((ActionBarActivity) context).getApplication();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutId, parent, false);

			holder = new ViewHolder();
			holder.ivUser = (ImageView) row.findViewById(R.id.iv_user);
			holder.tvTime = (TextView) row.findViewById(R.id.tv_time);
			holder.tvUser = (TextView) row.findViewById(R.id.tv_user);
			holder.tvContent = (TextView) row.findViewById(R.id.tv_content);

			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		holder.tvTime.setText(data.get(position).getCreatedAt());
		holder.tvUser.setText(data.get(position).getTwitterUser().getName());
		holder.tvContent.setText(data.get(position).getText());
		app.getImagesManager()
				.loadTwitterImage(data.get(position).getTwitterUser().getProfileImageUrl(), holder.ivUser);

		return row;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	private class ViewHolder {
		ImageView ivUser;
		TextView tvTime;
		TextView tvUser;
		TextView tvContent;
	}

}
