package com.brandappz.dhfl.event.database.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class SpeakersTable {
	
	public static final String TABLE_NAME = "speakers";
	
	public static class SpeakersColumns implements BaseColumns {
		public static final String NAME = "name";
		public static final String DISPLAY_ON_SCREEN = "display_on_screen";
		public static final String SURNAME = "surname";
		public static final String COMPANY = "company";
		public static final String PHOTO_ID = "photo_id";
		public static final String DESCRIPTION = "description";
	}
	
	public static void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + SpeakersTable.TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(SpeakersColumns.NAME + " TEXT, ");
		sb.append(SpeakersColumns.DISPLAY_ON_SCREEN + " TEXT, ");
		sb.append(SpeakersColumns.SURNAME + " TEXT, ");
		sb.append(SpeakersColumns.COMPANY + " TEXT, ");
		sb.append(SpeakersColumns.PHOTO_ID + " INTEGER, ");
		sb.append(SpeakersColumns.DESCRIPTION + " TEXT, ");
		sb.append("FOREIGN KEY(" + SpeakersColumns.PHOTO_ID + ") " +
				"REFERENCES " + PhotosTable.TABLE_NAME + "("
					+ BaseColumns._ID + ")");
		sb.append(");");
		db.execSQL(sb.toString());
	}
	
	public static void onUpgrade(SQLiteDatabase db,
			int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + SpeakersTable.TABLE_NAME);
		SpeakersTable.onCreate(db);
	}

}
