package com.anotherbrick.inthewall.datasource;

public class DSAccident {
	private float latitude;
	private float longitude;
	
	private float dimension;
	private boolean selected=false;
	
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

	public float getDimension() {
		return dimension;
	}

	public void setDimension(float dimension) {
		this.dimension = dimension;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
