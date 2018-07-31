package com.brandappz.dhfl.event.database.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class PhotosTable {
	
	public static final String TABLE_NAME = "photos";
	
	public static class PhotosColumns implements BaseColumns {
		public static final String FILENAME = "filename";
	}
	
	public static void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + PhotosTable.TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(PhotosColumns.FILENAME + " TEXT");
		sb.append(");");
		db.execSQL(sb.toString());
	}
	
	public static void onUpgrade(SQLiteDatabase db,
			int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + PhotosTable.TABLE_NAME);
		PhotosTable.onCreate(db);
	}

}
