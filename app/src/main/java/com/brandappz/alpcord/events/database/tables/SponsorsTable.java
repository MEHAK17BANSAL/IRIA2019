package com.brandappz.alpcord.events.database.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class SponsorsTable {
	
	public static final String TABLE_NAME = "sponsors";
	
	public static class SponsorsColumns implements BaseColumns {
		public static final String NAME = "name";
		public static final String WEBSITE_URL = "website_url";
		public static final String PHOTO_ID = "photo_id";
		public static final String DESCRIPTION = "description";
	}
	
	public static void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + SponsorsTable.TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(SponsorsColumns.NAME + " TEXT, ");
		sb.append(SponsorsColumns.WEBSITE_URL + " TEXT, ");
		sb.append(SponsorsColumns.PHOTO_ID + " INTEGER, ");
		sb.append(SponsorsColumns.DESCRIPTION + " TEXT, ");
		sb.append("FOREIGN KEY(" + SponsorsColumns.PHOTO_ID + ") " +
				"REFERENCES " + PhotosTable.TABLE_NAME + "("
					+ BaseColumns._ID + ")");
		sb.append(");");
		db.execSQL(sb.toString());
	}
	
	public static void onUpgrade(SQLiteDatabase db,
			int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + SponsorsTable.TABLE_NAME);
		SponsorsTable.onCreate(db);
	}

}
