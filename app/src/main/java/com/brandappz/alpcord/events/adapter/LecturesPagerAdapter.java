package com.brandappz.alpcord.events.adapter;

import java.util.List;

import com.brandappz.alpcord.events.fragments.LecturesFragment;
import com.brandappz.alpcord.events.model.DayTag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class LecturesPagerAdapter extends FragmentPagerAdapter {

	private final List<DayTag> data;

	public LecturesPagerAdapter(FragmentManager fragmentManager, List<DayTag> data) {
		super(fragmentManager);
		this.data = data;
	}

	@Override
	public Fragment getItem(int position) {
		LecturesFragment fragment = new LecturesFragment();
		Bundle args = new Bundle();
		args.putString(LecturesFragment.DAY_TAG, data.get(position).getId());
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return data.get(position).getTitle();
	}

}
