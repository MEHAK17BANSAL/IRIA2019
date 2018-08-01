package com.brandappz.alpcord.events.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.brandappz.alpcord.events.database.tables.PhotosTable;
import com.brandappz.alpcord.events.database.tables.PhotosTable.PhotosColumns;
import com.brandappz.alpcord.events.model.Photo;

public class PhotosDAO implements DAO<Photo> {

	public static final int PARAMETERS_QUANTITY = 2;

	private static final String INSERT = "insert into " + PhotosTable.TABLE_NAME + "(" + PhotosColumns._ID + ", "
			+ PhotosColumns.FILENAME + ") values (?, ?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public PhotosDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(PhotosDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindString(2, data[1]);
		return insertStatement.executeInsert();
	}

	@Override
	public void update(Photo data) {
		final ContentValues values = new ContentValues();
		values.put(PhotosColumns.FILENAME, data.getFilename());
		db.update(PhotosTable.TABLE_NAME, values, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(data.getId()) });
	}

	@Override
	public void remove(long id) {
		db.delete(PhotosTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
	}

	private Photo[] get(String condition, String[] params) {
		Photo[] photos = null;
		Cursor c = db.query(PhotosTable.TABLE_NAME, new String[] { PhotosColumns._ID, PhotosColumns.FILENAME },
				condition, params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return photos;
		}
		if (c.moveToFirst()) {
			photos = new Photo[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				photos[i] = new Photo();
				photos[i].setId(c.getLong(0));
				photos[i].setFilename(c.getString(1));

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return photos;
	}

	@Override
	public Photo get(long id) {
		Photo[] photo = get(BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
		if (photo == null) {
			return null;
		}
		return photo[0];
	}

	@Override
	public Photo[] getAll() {
		return get(null, null);
	}

}
