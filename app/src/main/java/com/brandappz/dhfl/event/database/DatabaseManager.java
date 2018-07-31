package com.brandappz.dhfl.event.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.brandappz.dhfl.event.database.dao.LecturesDAO;
import com.brandappz.dhfl.event.database.dao.PhotosDAO;
import com.brandappz.dhfl.event.database.dao.PlacesDAO;
import com.brandappz.dhfl.event.database.dao.SpeakersDAO;
import com.brandappz.dhfl.event.database.dao.SponsorsDAO;
import com.brandappz.dhfl.event.database.tables.SpeakersTable.SpeakersColumns;
import com.brandappz.dhfl.event.model.DayTag;
import com.brandappz.dhfl.event.model.Lecture;
import com.brandappz.dhfl.event.model.Photo;
import com.brandappz.dhfl.event.model.Place;
import com.brandappz.dhfl.event.model.Speaker;
import com.brandappz.dhfl.event.model.Sponsor;
import com.brandappz.framework.AppLog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DatabaseManager {

	private Context context;
	private SQLiteDatabase db;

	private PhotosDAO photosDAO;
	private PlacesDAO placesDAO;
	private SponsorsDAO sponsorsDAO;
	private SpeakersDAO speakersDAO;
	private LecturesDAO lecturesDAO;

	public DatabaseManager(Context context) {
		this.context = context;
		SQLiteOpenHelper openHelper = new OpenHelper(this.context);
		db = openHelper.getWritableDatabase();
		AppLog.logMessage("DEBUG", "DatabaseManager created, db open status: " + db.isOpen());

		photosDAO = new PhotosDAO(db);
		placesDAO = new PlacesDAO(db);
		sponsorsDAO = new SponsorsDAO(db);
		speakersDAO = new SpeakersDAO(db);
		lecturesDAO = new LecturesDAO(db);
	}

	public Photo getPhoto(long id) {
		return photosDAO.get(id);
	}

	public Place getPlace(long id) {
		return placesDAO.get(id);
	}

	public Sponsor getSponsor(long id) {
		Sponsor sponsor = sponsorsDAO.get(id);
		if (sponsor != null) {
			sponsor.setPhoto(getPhoto(sponsor.getPhoto().getId()));
		}
		return sponsor;
	}

	public Speaker getSpeaker(long id) {
		Speaker speaker = speakersDAO.get(id);
		if (speaker != null) {
			speaker.setPhoto(getPhoto(speaker.getPhoto().getId()));
		}
		return speaker;
	}

	public Lecture getLecture(long id) {
		Lecture lecture = lecturesDAO.get(id);
		if (lecture != null) {
			lecture.setPlace(getPlace(lecture.getPlace().getId()));
			lecture.setSpeaker(getSpeaker(lecture.getSpeaker().getId()));
		}
		return lecture;
	}

	public List<Place> getAllPlaces() {
		Place[] places = placesDAO.getAll();
		if (places == null)
			return new ArrayList<Place>();
		return Arrays.asList(places);
	}

	public List<Sponsor> getAllSponsors() {
		Sponsor[] sponsors = sponsorsDAO.getAll();
		if (sponsors == null)
			return new ArrayList<Sponsor>();
		for (Sponsor sponsor : sponsors) {
			sponsor.setPhoto(getPhoto(sponsor.getPhoto().getId()));
		}
		return Arrays.asList(sponsors);
	}

	public List<Speaker> getAllSpeakers() {
		Speaker[] speakers = speakersDAO.get(SpeakersColumns.DISPLAY_ON_SCREEN + " = ?",
				new String[] {"Y"});
		if (speakers == null)
			return new ArrayList<Speaker>();
		for (Speaker speaker : speakers) {
			speaker.setPhoto(getPhoto(speaker.getPhoto().getId()));
		}
		return Arrays.asList(speakers);
	}

	public List<Lecture> getAllLectures() {
		Lecture[] lectures = lecturesDAO.getAll();
		if (lectures == null)
			return new ArrayList<Lecture>();
		for (Lecture lecture : lectures) {
			lecture.setSpeaker(getSpeaker(lecture.getSpeaker().getId()));
		}
		return new ArrayList<Lecture>(Arrays.asList(lectures));
	}

	public List<Lecture> getLecturesForDay(Date day) {
		Lecture[] lectures = lecturesDAO.getForDay(day);
		if (lectures == null)
			return new ArrayList<Lecture>();
		for (Lecture lecture : lectures) {
			lecture.setSpeaker(getSpeaker(lecture.getSpeaker().getId()));
		}
		return Arrays.asList(lectures);
	}

	public List<Lecture> getFavouriteLectures() {
		Lecture[] lectures = lecturesDAO.getFavourites();
		if (lectures == null)
			return new ArrayList<Lecture>();
		for (Lecture lecture : lectures) {
			lecture.setSpeaker(getSpeaker(lecture.getSpeaker().getId()));
		}
		return Arrays.asList(lectures);
	}

	public List<Lecture> getLecturesForSpeaker(Speaker speaker) {
		Lecture[] lectures = lecturesDAO.getForSpeaker(speaker.getId());
		if (lectures == null)
			return new ArrayList<Lecture>();
		for (Lecture lecture : lectures) {
			lecture.setSpeaker(speaker);
		}
		return Arrays.asList(lectures);
	}

	public void rateLecture(long lectureId, float note) {
		lecturesDAO.rate(lectureId, note);
	}

	public void setLectureAsFavourite(long lectureId, boolean isFavourite) {
		lecturesDAO.setAsFavourite(lectureId, isFavourite);
	}

	public List<DayTag> getDayTags() {
		DayTag[] tags = lecturesDAO.getDaysTags();
		if (tags == null)
			return new ArrayList<DayTag>();
		return Arrays.asList(tags);
	}

}
