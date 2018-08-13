package com.ricardobevi.delivernow.entities;

import com.ricardobevi.delivernow.dto.OrderStatusDto;

public interface OrderStatus {

	OrderStatusDto asDto();
	
	OrderStatusDto asDto(String eta);

}
