package com.brandappz.dhfl.event.fragments;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brandappz.dhfl.event.ConferenceApp;
import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.adapter.TwitterTweetAdapter;
import com.brandappz.dhfl.event.constants.AppConstants;
import com.brandappz.dhfl.event.twitter.TwitterAPI;
import com.brandappz.dhfl.event.twitter.TwitterTweet;

public class TwitterFeedFragment extends Fragment {

	private static final String SCREEN_NAME = "i108";

	private List<TwitterTweet> data;
	private TwitterTweetAdapter adapter;
	private ListView list;
	private ProgressBar progressBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_rss, container, false);
		list = (ListView) rootView.findViewById(R.id.list);
		list.setSelector(android.R.color.transparent);
		progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

		if (ConferenceApp.isNetworkAvailable(getActivity())) {
			new GetTwitterTweetsAsyncTask().execute();
		} else {
			progressBar.setVisibility(View.GONE);
			Toast.makeText(getActivity(), R.string.network_unavailable, Toast.LENGTH_LONG).show();
		}

		return rootView;
	}

	private void refresh() {
		progressBar.setVisibility(View.GONE);
		if (data != null && getActivity() != null) {
			adapter = new TwitterTweetAdapter(getActivity(), data);
			list.setAdapter(adapter);
		}
	}

	private class GetTwitterTweetsAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			TwitterAPI twitterAPI = new TwitterAPI(AppConstants.TWITTER_API_KEY, AppConstants.TWITTER_API_SECRET);
			data = twitterAPI.getTwitterTweets(SCREEN_NAME);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			refresh();
		}

	}

}
