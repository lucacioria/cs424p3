package com.anotherbrick.inthewall.datasource;

public class DSAccident {
	private float latitude;
	private float longitude;
	
	public DSAccident(float latitude, float longitude){
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
}
