package com.brandappz.dhfl.event.database.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class PlacesTable {
	
	public static final String TABLE_NAME = "places";
	
	public static class PlacesColumns implements BaseColumns {
		public static final String NAME = "name";
		public static final String ADDRESS = "address";
		public static final String LATITUDE = "latitude";
		public static final String LONGITUDE = "longitude";
		public static final String DESCRIPTION = "description";
		
	}
	
	public static void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + PlacesTable.TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(PlacesColumns.NAME + " TEXT, ");
		sb.append(PlacesColumns.ADDRESS + " TEXT, ");
		sb.append(PlacesColumns.LATITUDE + " NUMBER, ");
		sb.append(PlacesColumns.LONGITUDE + " NUMBER, ");
		sb.append(PlacesColumns.DESCRIPTION + " TEXT ");
		sb.append(");");
		db.execSQL(sb.toString());
	}
	
	public static void onUpgrade(SQLiteDatabase db,
			int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + PlacesTable.TABLE_NAME);
		PlacesTable.onCreate(db);
	}
	
}
