package com.valicek.rocket.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
public class FlightDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Digits(message="Launching speed is required", fraction = 0, integer = 5)
	private Double speed;
	@Digits(message="Distance is required", fraction = 0, integer = 10)
	private Integer distance;
	@Digits(message="Launching height is required - even if it is zero", fraction = 0, integer = 10)
	private Integer height;
	@Digits(message="Launching angle is required", fraction = 0, integer = 3)
	private Double angle;
	@Digits(message="Launching wind speed is required - even if it is zero", fraction = 0, integer = 10)
	private Double windSpeed;
	private Date createdAt = new Date();
	
	private WindDirectionDto windDirectionDto;
	
	private RocketDto rocketDto;
	
}
