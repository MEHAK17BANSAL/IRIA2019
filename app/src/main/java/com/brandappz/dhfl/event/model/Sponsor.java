package com.brandappz.dhfl.event.model;

public class Sponsor extends ModelBase {
	
	protected String name;
	protected String websiteUrl;
	protected Photo photo;
	protected String description;
	
	public String getName() {
		return name;
	}
	
	public Photo getPhoto() {
		return photo;
	}
	
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}
