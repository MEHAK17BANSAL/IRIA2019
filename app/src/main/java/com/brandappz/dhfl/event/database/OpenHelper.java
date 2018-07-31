package com.brandappz.dhfl.event.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.brandappz.dhfl.event.constants.AppConstants;
import com.brandappz.dhfl.event.database.dao.DAO;
import com.brandappz.dhfl.event.database.dao.LecturesDAO;
import com.brandappz.dhfl.event.database.dao.PhotosDAO;
import com.brandappz.dhfl.event.database.dao.PlacesDAO;
import com.brandappz.dhfl.event.database.dao.SpeakersDAO;
import com.brandappz.dhfl.event.database.dao.SponsorsDAO;
import com.brandappz.dhfl.event.database.tables.LecturesTable;
import com.brandappz.dhfl.event.database.tables.PhotosTable;
import com.brandappz.dhfl.event.database.tables.PlacesTable;
import com.brandappz.dhfl.event.database.tables.SpeakersTable;
import com.brandappz.dhfl.event.database.tables.SponsorsTable;
import com.brandappz.framework.AppLog;

public class OpenHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private Context context;

	OpenHelper(final Context context) {
		super(context, AppConstants.DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	public void onOpen(final SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			db.execSQL("PRAGMA foreign_keys=ON;");
			Cursor c = db.rawQuery("PRAGMA foreign_keys", null);
			if (c.moveToFirst()) {
				int result = c.getInt(0);
				AppLog.logMessage("DEBUG", "SQLite foreign key support (1 is on, 0 is off): " + result);
			} else {
				AppLog.logMessage("DEBUG", "SQLite foreign key support NOT AVAILABLE");
			}
			if (!c.isClosed()) {
				c.close();
			}
		}
	}

	public void onCreate(final SQLiteDatabase db) {
		PhotosTable.onCreate(db);
		PlacesTable.onCreate(db);
		SponsorsTable.onCreate(db);
		SpeakersTable.onCreate(db);
		LecturesTable.onCreate(db);

		loadContent(db);
	}

	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
		PhotosTable.onUpgrade(db, oldVersion, newVersion);
		PlacesTable.onUpgrade(db, oldVersion, newVersion);
		SponsorsTable.onUpgrade(db, oldVersion, newVersion);
		SpeakersTable.onUpgrade(db, oldVersion, newVersion);
		LecturesTable.onUpgrade(db, oldVersion, newVersion);

		loadContent(db);
	}

	private void loadContent(final SQLiteDatabase db) {
		loadFileIntoDatabase(new PhotosDAO(db), "photos.txt", PhotosDAO.PARAMETERS_QUANTITY);
		loadFileIntoDatabase(new PlacesDAO(db), "places.txt", PlacesDAO.PARAMETERS_QUANTITY);
		loadFileIntoDatabase(new SponsorsDAO(db), "sponsors.txt", SponsorsDAO.PARAMETERS_QUANTITY);
		loadFileIntoDatabase(new SpeakersDAO(db), "speakers.txt", SpeakersDAO.PARAMETERS_QUANTITY);
		loadFileIntoDatabase(new LecturesDAO(db), "lectures.txt", LecturesDAO.PARAMETERS_QUANTITY);
	}

	@SuppressWarnings("rawtypes")
	private void loadFileIntoDatabase(DAO dao, String filename, int record_length) {
		try {
			InputStream is = context.getAssets().open(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			int line_nr = 1;
			while ((line = reader.readLine()) != null) {
				String[] record = line.split(AppConstants.DATA_SEPARATOR);
				if (record.length != record_length) {
					AppLog.logMessage("WARNING", "Incorrect record in file " + filename + "[line: " + line_nr + "]\n" + "Record elements: "
							+ record.length + ", expected: " + record_length);
				} else {
					dao.insert(record);
				}
				line_nr++;
			}
		} catch (IOException e) {
			AppLog.logErrorMessage("ERROR", "IOException: load data from " + filename, e);
		}
	}

}