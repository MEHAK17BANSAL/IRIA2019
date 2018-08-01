package com.brandappz.alpcord.events.activities.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.fragments.LocationsFragment;

public class LocationsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_details);

		Fragment fragment = new LocationsFragment();
		fragment.setArguments(getIntent().getExtras());
		getSupportFragmentManager().beginTransaction().replace(R.id.details, fragment).commit();
	}

	public static void startActivity(Context context, Bundle args) {
		Intent intent = new Intent(context, LocationsActivity.class);
		intent.putExtras(args);
		context.startActivity(intent);
	}

}
