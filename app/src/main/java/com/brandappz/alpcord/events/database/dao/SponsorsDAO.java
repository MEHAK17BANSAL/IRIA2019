package com.brandappz.alpcord.events.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.brandappz.alpcord.events.database.tables.SponsorsTable;
import com.brandappz.alpcord.events.database.tables.SponsorsTable.SponsorsColumns;
import com.brandappz.alpcord.events.model.Photo;
import com.brandappz.alpcord.events.model.Sponsor;

public class SponsorsDAO implements DAO<Sponsor> {

	public static final int PARAMETERS_QUANTITY = 5;

	private static final String INSERT = "insert into " + SponsorsTable.TABLE_NAME + "(" + SponsorsColumns._ID + ", "
			+ SponsorsColumns.NAME + ", " + SponsorsColumns.WEBSITE_URL + ", " + SponsorsColumns.PHOTO_ID + ", "
			+ SponsorsColumns.DESCRIPTION + ") values (?, ?, ?, ?, ?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public SponsorsDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(SponsorsDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindString(2, data[1]);
		insertStatement.bindString(3, data[2]);
		insertStatement.bindLong(4, Long.valueOf(data[3]));
		insertStatement.bindString(5, data[4]);
		return insertStatement.executeInsert();
	}

	@Override
	public void update(Sponsor data) {
		final ContentValues values = new ContentValues();
		values.put(SponsorsColumns.NAME, data.getName());
		values.put(SponsorsColumns.WEBSITE_URL, data.getWebsiteUrl());
		values.put(SponsorsColumns.PHOTO_ID, data.getPhoto().getId());
		values.put(SponsorsColumns.DESCRIPTION, data.getDescription());
		db.update(SponsorsTable.TABLE_NAME, values, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(data.getId()) });
	}

	@Override
	public void remove(long id) {
		db.delete(SponsorsTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
	}

	private Sponsor[] get(String condition, String[] params) {
		Sponsor[] sponsors = null;
		Cursor c = db.query(SponsorsTable.TABLE_NAME, new String[] { SponsorsColumns._ID, SponsorsColumns.NAME,
				SponsorsColumns.WEBSITE_URL, SponsorsColumns.PHOTO_ID, SponsorsColumns.DESCRIPTION }, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return sponsors;
		}
		if (c.moveToFirst()) {
			sponsors = new Sponsor[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				sponsors[i] = new Sponsor();
				sponsors[i].setId(c.getLong(0));
				sponsors[i].setName(c.getString(1));
				sponsors[i].setWebsiteUrl(c.getString(2));

				Photo photo = new Photo();
				photo.setId(c.getLong(3));
				sponsors[i].setPhoto(photo);
				
				sponsors[i].setDescription(c.getString(4));

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return sponsors;
	}

	@Override
	public Sponsor get(long id) {
		Sponsor[] sponsor = get(BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
		if (sponsor == null) {
			return null;
		}
		return sponsor[0];
	}

	@Override
	public Sponsor[] getAll() {
		return get(null, null);
	}

}
