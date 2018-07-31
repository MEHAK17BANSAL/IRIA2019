package com.brandappz.dhfl.event.model;

import java.util.Date;

public class Lecture extends ModelBase {
	
	protected String title;
	protected Date startTime;
	protected Date endTime;
	protected boolean favourite;
	protected String type;
	protected String description;
	protected Place place;
	protected Speaker speaker;
	protected float note;
	
	public String getDescription() {
		return description;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public float getNote() {
		return note;
	}
	
	public Place getPlace() {
		return place;
	}
	
	public Speaker getSpeaker() {
		return speaker;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isFavourite() {
		return favourite;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}
	
	public void setNote(float note) {
		this.note = note;
	}
	
	public void setPlace(Place place) {
		this.place = place;
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
