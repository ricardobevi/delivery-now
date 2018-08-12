package com.ricardobevi.delivernow.dto;

public class LatLongLocationDto {

	private final Double latitude;
	private final Double longitude;
	
	public LatLongLocationDto(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	
}
