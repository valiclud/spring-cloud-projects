package tacos.dto;

import java.util.Date;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TacoOrderRecordDto(String ccNumber,
		@Pattern(regexp = "^(0[1-9]|1[0-2])(\\/)([2-9][0-9])$", message = "Must be formatted MM/YY") String ccExpiration,
		@Digits(integer = 3, fraction = 0, message = "Invalid CVV") String ccCvv,
		@NotBlank(message = "Delivery name is required") String deliveryName,
		@NotBlank(message = "Delivery street is required") String deliveryStreet, 
		@NotBlank(message = "Delivery city is required") String deliveryCity, 
		@NotBlank(message = "Delivery state is required") String deliveryState,
		@NotBlank(message = "ZIP code is required") String deliveryZip,
		Date placedAt, Long clientId) {
	
}