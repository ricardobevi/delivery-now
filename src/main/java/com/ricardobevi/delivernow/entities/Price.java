package com.ricardobevi.delivernow.entities;

public class Price {

	private Double value;
	
	public Price(Double value) {
		this.value = value;
	}
	
	public Price sum(Price price) {
		return new Price(Double.sum(this.value, price.value));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	
}
