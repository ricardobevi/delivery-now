package com.ricardobevi.delivernow.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

	public static LatLongLocationDto parseString(String latLong) {
		List<Double> latLongDouble = Arrays.asList(latLong.split(",")).stream().map(s -> Double.parseDouble(s)).collect(Collectors.toList());
		return new LatLongLocationDto(latLongDouble.get(0), latLongDouble.get(1));
	}
	
	
}
