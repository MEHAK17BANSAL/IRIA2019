package com.brandappz.dhfl.event.model;

import com.google.android.gms.maps.model.LatLng;

public class Place extends ModelBase {

	protected String name;
	protected String address;
	protected double latitude;
	protected double longitude;
	protected String description;

	public String getAddress() {
		return address;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	

	public void setAddress(String address) {
		this.address = address;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LatLng getPosition() {
		return new LatLng(latitude, longitude);
	}

	public void setDescription(String description) {
		this.description=description ;
	}

}
