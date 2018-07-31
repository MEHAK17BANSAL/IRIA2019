package com.brandappz.dhfl.event.model;

public class Speaker extends ModelBase {

	protected String name;
	protected boolean displayOnScreen;
	protected String surname;
	protected String company;
	protected String description;
	protected Photo photo;

	public String getCompany() {
		return company;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Photo getPhoto() {
		return photo;
	}

	public String getSurname() {
		return surname;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isDisplayOnScreen() {
		return displayOnScreen;
	}

	public void setDisplayOnScreen(boolean displayOnScreen) {
		this.displayOnScreen = displayOnScreen;
	}

}
