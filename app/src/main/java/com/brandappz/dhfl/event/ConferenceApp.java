package com.brandappz.dhfl.event;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.brandappz.dhfl.event.database.DatabaseManager;
import com.brandappz.dhfl.event.utils.ImagesManager;
import com.brandappz.framework.BaseUtil;
import com.pushbots.push.Pushbots;

public class ConferenceApp extends Application {
	public static final String TAG = ConferenceApp.class
			.getSimpleName();

	private RequestQueue mRequestQueue;

	private static ConferenceApp mInstance;

	private DatabaseManager dbManager;
	private ImagesManager imagesManager;

	@Override
	public void onCreate() {
		super.onCreate();
		BaseUtil.debugApp= true;
		//Pushbots.init(this, "426871778008","58ac16044a9efa53098b4567");
		Pushbots.sharedInstance().init(this);
		mInstance = this;
		Pushbots.sharedInstance().setCustomHandler(pushbotsHandler.class);
		dbManager = new DatabaseManager(this);
		imagesManager = new ImagesManager(this);
	}
	public static synchronized ConferenceApp getInstance() {
		return mInstance;
	}
	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
	public DatabaseManager getDbManager() {
		return dbManager;
	}

	public ImagesManager getImagesManager() {
		return imagesManager;
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	

}
