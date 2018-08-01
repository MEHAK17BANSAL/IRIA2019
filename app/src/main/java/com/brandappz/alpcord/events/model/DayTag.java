package com.brandappz.alpcord.events.model;

import java.util.Calendar;
import java.util.Date;

import com.brandappz.alpcord.events.utils.Utils;

public class DayTag {

	protected Calendar calendar;

	public DayTag(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
	}

	public String getId() {
		return Utils.dateToString(calendar.getTime());
	}

	public String getTitle() {
//		AppLog.logMessage("DayTag", "DayTag::getTitle:"+new SimpleDateFormat("MMM").format(calendar.getTime()));
//		AppLog.logMessage("DayTag", "DayTag::getTitle:"+Utils.dateToString(calendar.getTime(), "MMM-dd-yy"));
		return Utils.dateToString(calendar.getTime(), "dd MMM yy");
//		return calendar.get(Calendar.DAY_OF_MONTH) + "."
//				+ String.format("%02d", calendar.get(Calendar.MONTH) + 1) + ".";
		//return calendar.get(Calendar.DAY_OF_MONTH) + "."+ String.format("%02d", calendar.get(Calendar.MONTH) + 1) + ".";
	}

	public int getDayOfWeek() {
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

}
