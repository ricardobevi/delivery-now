package com.ricardobevi.delivernow.entities;

public class Address {

	private final String address;
	
	public Address(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return this.address;
	}

}
