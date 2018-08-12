package com.ricardobevi.delivernow.entities;

import com.ricardobevi.delivernow.dto.LatLongLocationDto;

public class LatLongLocation {

	private final Double latitude; 
	private final Double longitude;
	
	public LatLongLocation(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public LatLongLocation(LatLongLocationDto latLong) {
		this.latitude = latLong.getLatitude();
		this.longitude = latLong.getLongitude();
	}

	public LatLongLocationDto asDto() {
		return new LatLongLocationDto(this.latitude, this.longitude);
	}

}
