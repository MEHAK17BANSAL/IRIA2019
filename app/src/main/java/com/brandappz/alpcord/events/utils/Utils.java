package com.brandappz.alpcord.events.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.DisplayMetrics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	private static final String TIME_FORMAT = "hh:mm a";
	private static final String DAY_FORMAT = "dd.MMM";

	public static String dateToString(Date date) {
		return dateToString(date, DATE_FORMAT);
//		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
//		return sdf.format(date);
	}

	public static String dateToString(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
		return sdf.format(date);
	}

	
	
	public static Date stringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String getTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
		return sdf.format(date);
	}
	/*public static String endTime(String date){
		String stz = new String(END_TIME, Locale.getDefault());
		return String.format(stz);
	}*/
	public static boolean isOnline(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();

		if (netinfo != null && netinfo.isConnectedOrConnecting()) {
			android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

			if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
				return true;
			else return false;
		} else
			return false;
	}
	public static boolean isOffline(Context context) {
		return !isOnline(context);
	}

	public static String getDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT, Locale.getDefault());
		return sdf.format(date);
	}

	public static int convertDpToPixels(Context context, int dp) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		int px = (int) (dp * (metrics.densityDpi / 160f));
		return px;
	}

	public static void openUrl(Context context, String url) {
		Uri uri = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(intent);
	}

}
