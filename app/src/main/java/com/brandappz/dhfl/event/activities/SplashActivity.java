package com.brandappz.dhfl.event.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.utils.Utils;
import com.brandappz.framework.BaseUtil;

public class SplashActivity extends AppCompatActivity {

	private static int SPLASH_TIME_OUT = 2000;
	private Handler handler;
	private Runnable starter;
	String flag;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
		BaseUtil.debugApp = false;
		MultiDex.install(this);
		StartScreen();

	}

	private void StartScreen() {
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
				SharedPreferences pref = getApplicationContext().getSharedPreferences("DashBoard", 0);
				SharedPreferences.Editor editor = pref.edit();
				flag = pref.getString("login", "");
				if (Utils.isOnline(SplashActivity.this)) {
					String otp = "";
					if (flag != null && flag.equalsIgnoreCase("true")) {
						Intent i = new Intent(SplashActivity.this, MainActivity.class);
						startActivity(i);
						finish();
					} else {
						Intent i = new Intent(SplashActivity.this, MainActivity.class);
						startActivity(i);
						finish();
					}
				} else {
					Toast.makeText(SplashActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
				}
				return;

			}
		}, SPLASH_TIME_OUT);
	}


	
	@Override
	public void onBackPressed() {
		handler.removeCallbacks(starter);
		super.onBackPressed();
	}

	
	

}
