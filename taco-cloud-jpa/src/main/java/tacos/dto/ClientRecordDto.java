package tacos.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public record ClientRecordDto(Long id,
		@NotBlank(message = "Delivery name is required") String deliveryName,
		@NotBlank(message = "Delivery street is required") String deliveryStreet, 
		@NotBlank(message = "Delivery city is required") String deliveryCity, 
		@NotBlank(message = "Delivery state is required") String deliveryState,
		@NotBlank(message = "ZIP code is required") String deliveryZip,
		Date createdAt) {
	
}