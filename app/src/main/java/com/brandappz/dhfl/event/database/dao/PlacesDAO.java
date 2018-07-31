package com.brandappz.dhfl.event.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.brandappz.dhfl.event.database.tables.PlacesTable;
import com.brandappz.dhfl.event.database.tables.PlacesTable.PlacesColumns;
import com.brandappz.dhfl.event.model.Place;

public class PlacesDAO implements DAO<Place> {

	public static final int PARAMETERS_QUANTITY = 6;

	private static final String INSERT = "insert into " + PlacesTable.TABLE_NAME + "(" + PlacesColumns._ID + ", "
			+ PlacesColumns.NAME + ", " + PlacesColumns.ADDRESS + ", " + PlacesColumns.LATITUDE + ", "
			+ PlacesColumns.LONGITUDE + ", " + PlacesColumns.DESCRIPTION + ") values (?, ?, ?, ?, ?, ?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public PlacesDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(PlacesDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindString(2, data[1]);
		insertStatement.bindString(3, data[2]);
		insertStatement.bindDouble(4, Double.valueOf(data[3]));
		insertStatement.bindDouble(5, Double.valueOf(data[4]));
		insertStatement.bindString(6, data[5]);
		return insertStatement.executeInsert();
	}

	@Override
	public void update(Place data) {
		final ContentValues values = new ContentValues();
		values.put(PlacesColumns.NAME, data.getName());
		values.put(PlacesColumns.ADDRESS, data.getAddress());
		values.put(PlacesColumns.LATITUDE, data.getLatitude());
		values.put(PlacesColumns.LONGITUDE, data.getLongitude());
		values.put(PlacesColumns.DESCRIPTION, data.getDescription());
		db.update(PlacesTable.TABLE_NAME, values, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(data.getId()) });
	}

	@Override
	public void remove(long id) {
		db.delete(PlacesTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
	}

	private Place[] get(String condition, String[] params) {
		Place[] places = null;
		Cursor c = db.query(PlacesTable.TABLE_NAME, new String[] { PlacesColumns._ID, PlacesColumns.NAME,
				PlacesColumns.ADDRESS, PlacesColumns.LATITUDE, PlacesColumns.LONGITUDE, PlacesColumns.DESCRIPTION }, condition, params, null,
				null, null);
		if (c.getCount() == 0) {
			c.close();
			return places;
		}
		if (c.moveToFirst()) {
			places = new Place[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				places[i] = new Place();
				places[i].setId(c.getLong(0));
				places[i].setName(c.getString(1));
				places[i].setAddress(c.getString(2).replaceAll("\\\\n", "\n"));
				places[i].setLatitude(c.getDouble(3));
				places[i].setLongitude(c.getDouble(4));
				places[i].setDescription(c.getString(5).replaceAll("\\\\n", "\n"));

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return places;
	}

	@Override
	public Place get(long id) {
		Place[] place = get(BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
		if (place == null) {
			return null;
		}
		return place[0];
	}

	@Override
	public Place[] getAll() {
		return get(null, null);
	}

}
