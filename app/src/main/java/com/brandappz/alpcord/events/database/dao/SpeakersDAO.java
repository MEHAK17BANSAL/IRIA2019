package com.brandappz.alpcord.events.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.brandappz.alpcord.events.database.tables.SpeakersTable;
import com.brandappz.alpcord.events.database.tables.SpeakersTable.SpeakersColumns;
import com.brandappz.alpcord.events.model.Photo;
import com.brandappz.alpcord.events.model.Speaker;

public class SpeakersDAO implements DAO<Speaker> {

	public static final int PARAMETERS_QUANTITY = 7;

	private static final String INSERT = "insert into "
			+ SpeakersTable.TABLE_NAME + "(" + SpeakersColumns._ID + ", "
			+ SpeakersColumns.DISPLAY_ON_SCREEN + ", " + SpeakersColumns.NAME
			+ ", " + SpeakersColumns.SURNAME + ", " + SpeakersColumns.COMPANY
			+ ", " + SpeakersColumns.PHOTO_ID + ", "
			+ SpeakersColumns.DESCRIPTION + ") values (?, ?, ?, ?, ?, ?, ?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public SpeakersDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(SpeakersDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindString(2, data[1]);
		insertStatement.bindString(3, data[2]);
		insertStatement.bindString(4, data[3]);
		insertStatement.bindString(5, data[4]);
		insertStatement.bindLong(6, Long.valueOf(data[5]));
		insertStatement.bindString(7, data[6]);
		return insertStatement.executeInsert();
	}

	@Override
	public void update(Speaker data) {
		final ContentValues values = new ContentValues();
		values.put(SpeakersColumns.NAME, data.getName());
		values.put(SpeakersColumns.DISPLAY_ON_SCREEN, (data.isDisplayOnScreen()?"Y":"N"));
		values.put(SpeakersColumns.SURNAME, data.getSurname());
		values.put(SpeakersColumns.COMPANY, data.getCompany());
		values.put(SpeakersColumns.PHOTO_ID, data.getPhoto().getId());
		values.put(SpeakersColumns.DESCRIPTION, data.getDescription());
		db.update(SpeakersTable.TABLE_NAME, values, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(data.getId()) });
	}

	@Override
	public void remove(long id) {
		db.delete(SpeakersTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	public  Speaker[] get(String condition, String[] params) {
		Speaker[] speakers = null;
		Cursor c = db.query(SpeakersTable.TABLE_NAME, new String[] {
				SpeakersColumns._ID, SpeakersColumns.NAME,SpeakersColumns.DISPLAY_ON_SCREEN,
				SpeakersColumns.SURNAME, SpeakersColumns.COMPANY,
				SpeakersColumns.PHOTO_ID, SpeakersColumns.DESCRIPTION },
				condition, params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return speakers;
		}
		if (c.moveToFirst()) {
			speakers = new Speaker[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				speakers[i] = new Speaker();
				
				speakers[i].setId(c.getLong(0));
				speakers[i].setName(c.getString(1));
				speakers[i].setDisplayOnScreen((c.getString(2).equalsIgnoreCase("Y")));
				speakers[i].setSurname(c.getString(3));
				speakers[i].setCompany(c.getString(4));

				Photo photo = new Photo();
				photo.setId(c.getLong(5));
				speakers[i].setPhoto(photo);

				speakers[i].setDescription(c.getString(6).replaceAll("\\\\n", "\n"));

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return speakers;
	}

	@Override
	public Speaker get(long id) {
		Speaker[] speaker = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (speaker == null) {
			return null;
		}
		return speaker[0];
	}

	@Override
	public Speaker[] getAll() {
		return get(null, null);
	}

}
