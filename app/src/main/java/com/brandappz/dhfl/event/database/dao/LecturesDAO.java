package com.brandappz.dhfl.event.database.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.brandappz.dhfl.event.database.tables.LecturesTable;
import com.brandappz.dhfl.event.database.tables.LecturesTable.LecturesColumns;
import com.brandappz.dhfl.event.model.DayTag;
import com.brandappz.dhfl.event.model.Lecture;
import com.brandappz.dhfl.event.model.Place;
import com.brandappz.dhfl.event.model.Speaker;
import com.brandappz.dhfl.event.utils.Utils;
import com.brandappz.framework.AppLog;

public class LecturesDAO implements DAO<Lecture> {

	public static final int PARAMETERS_QUANTITY = 8;

	private static final String INSERT = "insert into " + LecturesTable.TABLE_NAME + "(" + LecturesColumns._ID + ", "
			+ LecturesColumns.TITLE + ", " + LecturesColumns.SPEAKER_ID + ", " + LecturesColumns.START_TIME + ", "
			+ LecturesColumns.END_TIME + ", " + LecturesColumns.IS_FAVOURITE + ", " + LecturesColumns.TYPE + ", "
			+ LecturesColumns.PLACE_ID + ", " + LecturesColumns.DESCRIPTION + ", " + LecturesColumns.NOTE
			+ ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final int TRUE = 1;
	private static final int FALSE = 2;

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public LecturesDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(LecturesDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindString(2, data[1]);
		insertStatement.bindLong(3, Long.valueOf(data[2]));
		insertStatement.bindString(4, data[3]);
		insertStatement.bindString(5, data[4]);
		insertStatement.bindLong(6, FALSE);
		insertStatement.bindString(7, data[5]);
		insertStatement.bindLong(8, Long.valueOf(data[6]));
		insertStatement.bindString(9, data[7]);
		insertStatement.bindLong(10, 0);
		return insertStatement.executeInsert();
	}

	@Override
	public void update(Lecture lecture) {
		final ContentValues values = new ContentValues();
		values.put(LecturesColumns.TITLE, lecture.getTitle());
		values.put(LecturesColumns.SPEAKER_ID, lecture.getSpeaker().getId());
		values.put(LecturesColumns.START_TIME, Utils.dateToString(lecture.getStartTime()));
		values.put(LecturesColumns.END_TIME, Utils.dateToString(lecture.getEndTime()));
		values.put(LecturesColumns.TYPE, lecture.getType());
		values.put(LecturesColumns.PLACE_ID, lecture.getPlace().getId());
		values.put(LecturesColumns.DESCRIPTION, lecture.getDescription());
		db.update(LecturesTable.TABLE_NAME, values, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(lecture.getId()) });
	}

	@Override
	public void remove(long id) {
		db.delete(LecturesTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
	}

	private Lecture[] get(String condition, String[] params) {
		Lecture[] lectures = null;
		Cursor c = db.query(LecturesTable.TABLE_NAME, new String[] { LecturesColumns._ID, LecturesColumns.TITLE,
				LecturesColumns.SPEAKER_ID, LecturesColumns.START_TIME, LecturesColumns.END_TIME,
				LecturesColumns.IS_FAVOURITE, LecturesColumns.TYPE, LecturesColumns.PLACE_ID,
				LecturesColumns.DESCRIPTION, LecturesColumns.NOTE }, condition, params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return lectures;
		}
		if (c.moveToFirst()) {
			lectures = new Lecture[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				lectures[i] = new Lecture();
				lectures[i].setId(c.getLong(0));
				lectures[i].setTitle(c.getString(1));

				Speaker speaker = new Speaker();
				speaker.setId(c.getLong(2));
				lectures[i].setSpeaker(speaker);

				lectures[i].setStartTime(Utils.stringToDate(c.getString(3)));
				lectures[i].setEndTime(Utils.stringToDate(c.getString(4)));

				lectures[i].setFavourite(c.getInt(5) == TRUE);
				lectures[i].setType(c.getString(6));

				Place place = new Place();
				place.setId(c.getLong(7));
				lectures[i].setPlace(place);

				lectures[i].setDescription(c.getString(8).replaceAll("\\\\n", "\n"));
//				AppLog.logMessage("LecturesDAO", "LecturesDAO:c.getString(8):"+c.getString(8).replaceAll("\\\\n", "\n"));
//				AppLog.logMessage("LecturesDAO", "LecturesDAO:getDescription:"+lectures[i].getDescription().replaceAll("\\\\n", "\n"));
				lectures[i].setNote(c.getFloat(9));

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return lectures;
	}

	@Override
	public Lecture get(long id) {
		Lecture[] lecture = get(BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
		if (lecture == null) {
			return null;
		}
		return lecture[0];
	}

	public Lecture[] getFavourites() {
		return get(LecturesColumns.IS_FAVOURITE + " = ?", new String[] { String.valueOf(TRUE) });
	}

	public Lecture[] getForDay(Date day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		return get(LecturesColumns.START_TIME + " LIKE '" + sdf.format(day) + "%'", null);
	}

	public Lecture[] getForSpeaker(long speakerId) {
		return get(LecturesColumns.SPEAKER_ID + " = ?", new String[] { String.valueOf(speakerId) });
	}

	@Override
	public Lecture[] getAll() {
		return get(null, null);
	}

	public DayTag[] getDaysTags() {
		DayTag[] res = null;
		Cursor c = db.query(true, LecturesTable.TABLE_NAME, new String[] { "SUBSTR(" + LecturesColumns.START_TIME
				+ ",1,10)" }, null, null, LecturesColumns.START_TIME, null, null, null);
		res = new DayTag[c.getCount()];
		c.moveToFirst();
		for (int i = 0; i < res.length; i++) {
			res[i] = new DayTag(Utils.stringToDate(c.getString(0) + " 00:00"));
			AppLog.logMessage("", "c.getString(0):"+c.getString(0)+":res["+i+"]:"+res[i]);
			c.moveToNext();
		}
		if (!c.isClosed()) {
			c.close();
		}
		return res;
	}

	public void setAsFavourite(long id, boolean isFavourite) {
		final ContentValues values = new ContentValues();
		values.put(LecturesColumns.IS_FAVOURITE, isFavourite ? TRUE : FALSE);
		db.update(LecturesTable.TABLE_NAME, values, BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
	}

	public void rate(long id, float note) {
		final ContentValues values = new ContentValues();
		values.put(LecturesColumns.NOTE, note);
		db.update(LecturesTable.TABLE_NAME, values, BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
	}

}
