package com.brandappz.dhfl.event.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.brandappz.dhfl.event.ConferenceApp;
import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.model.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class LocationsFragment extends Fragment implements OnMapReadyCallback {

	public static final String PLACE_ID = "placeId";

	private ConferenceApp app;
	private GoogleMap map;
	private SupportMapFragment mapFragment;
	private List<Place> places;
	public WebView mWebView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_locations, container, false);
		mWebView = (WebView) v.findViewById(R.id.webvu3);
		mWebView.setWebViewClient(new WebViewClient());
		mWebView.setInitialScale((int) (100 * mWebView.getScale()));

		final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);


		mWebView.setInitialScale(1);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setSupportZoom(true);
		mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);

		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.setScrollbarFadingEnabled(false);

		mWebView.loadUrl("http://socialcampaign.co.in/apps/event_management/dhfl/quiz/CidadeDeGoa.html");
		mWebView.setWebViewClient(new WebViewClient() {

			@SuppressWarnings("deprecation")
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				dialog.show();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				dialog.dismiss();
				String webUrl = mWebView.getUrl();
			}


		});


		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);


		// Inflate the layout for this fragment
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}


	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_locations, container, false);
		app = (ConferenceApp) getActivity().getApplication();

		mapFragment = SupportMapFragment.newInstance();
		getChildFragmentManager().beginTransaction().replace(R.id.map_layout, mapFragment).commit();

		long id = getArguments() == null ? -1 : getArguments().getLong(PLACE_ID, -1);
		if (id < 0)
			places = app.getDbManager().getAllPlaces();
		else {
			places = new ArrayList<Place>();
			places.add(app.getDbManager().getPlace(id));
		}

		return rootView;
	}*/

/*
	@Override
	public void onResume() {
		if (map == null) {
			//map = mapFragment.getMap();
//			prepareMap();
		}
		super.onResume();
	}
*/

	private void prepareMap() {
		if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		map.setMyLocationEnabled(true);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(places.get(0).getPosition(), 13));
		for (int i = 0; i < places.size(); i++) {
			map.addMarker(new MarkerOptions().title(places.get(i).getName()).position(places.get(i).getPosition())).showInfoWindow();
			
		}
	}

	@Override
	public void onMapReady(GoogleMap arg0) {
		map = arg0;
		prepareMap();
	}

}
