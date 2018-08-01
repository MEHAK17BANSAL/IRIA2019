package com.brandappz.alpcord.events.database.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class LecturesTable {
	
	public static final String TABLE_NAME = "lectures";
	
	public static class LecturesColumns implements BaseColumns {
		public static final String TITLE = "title";
		public static final String SPEAKER_ID = "speaker_id";
		public static final String START_TIME = "start_time";
		public static final String END_TIME = "end_time";
		public static final String IS_FAVOURITE = "is_favourite";
		public static final String TYPE = "lecture_type";
		public static final String PLACE_ID = "place_id";
		public static final String DESCRIPTION = "description";
		public static final String NOTE = "note";
	}
	
	public static void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + LecturesTable.TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(LecturesColumns.TITLE + " TEXT, ");
		sb.append(LecturesColumns.SPEAKER_ID + " INTEGER, ");
		sb.append(LecturesColumns.START_TIME + " DATETIME, ");
		sb.append(LecturesColumns.END_TIME + " DATETIME, ");
		sb.append(LecturesColumns.IS_FAVOURITE + " INTEGER, ");
		sb.append(LecturesColumns.TYPE + " TEXT, ");
		sb.append(LecturesColumns.PLACE_ID + " INTEGER, ");
		sb.append(LecturesColumns.DESCRIPTION + " TEXT, ");
		sb.append(LecturesColumns.NOTE + " REAL, ");
		sb.append("FOREIGN KEY(" + LecturesColumns.SPEAKER_ID + ") " +
				"REFERENCES " + SpeakersTable.TABLE_NAME + "("
					+ BaseColumns._ID + "), ");
		sb.append("FOREIGN KEY(" + LecturesColumns.PLACE_ID + ") " +
				"REFERENCES " + PlacesTable.TABLE_NAME + "("
					+ BaseColumns._ID + ")");
		sb.append(");");
		db.execSQL(sb.toString());
	}
	
	public static void onUpgrade(SQLiteDatabase db,
			int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + LecturesTable.TABLE_NAME);
		LecturesTable.onCreate(db);
	}

}
