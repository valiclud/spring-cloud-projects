package com.valicek.rocket.dto;

import com.valicek.rocket.entity.Flight;
import com.valicek.rocket.entity.Rocket;
import com.valicek.rocket.entity.WindDirection;

public class Helper {

	public static FlightDto toFlightDto(Flight flight) {
		FlightDto flightDto = new FlightDto();
		flightDto.setSpeed(flight.getSpeed());
		flightDto.setDistance(flight.getDistance());
		flightDto.setHeight(flight.getHeight());
		flightDto.setAngle(flight.getAngle());
		flightDto.setWindSpeed(flight.getWindSpeed());
		flightDto.setCreatedAt(flight.getCreatedAt());
		flightDto.setWindDirectionDto(toWindDirectionDto(flight.getWindDirection()));
		flightDto.setRocketDto(toRocketDto(flight.getRocket()));
		
		return flightDto;
	}

	public static Flight toFlight(FlightDto flightDto) {
		Flight flight = new Flight();
		
		flight.setSpeed(flightDto.getSpeed());
		flight.setDistance(flightDto.getDistance());
		flight.setHeight(flightDto.getHeight());
		flight.setAngle(flightDto.getAngle());
		flight.setWindSpeed(flightDto.getWindSpeed());
		flight.setCreatedAt(flightDto.getCreatedAt());
		flight.setWindDirection(toWindDirection(flightDto.getWindDirectionDto()));
		flight.setRocket(toRocket(flightDto.getRocketDto()));
		
		return flight;
	}
	
	public static RocketDto toRocketDto(Rocket rocket) {
		RocketDto dto = new RocketDto();
		dto.setId(rocket.getId());
		dto.setAngleMax(rocket.getAngleMax());
		dto.setAngleMin(rocket.getAngleMin());
		dto.setCreatedAt(rocket.getCreatedAt());
		dto.setDistanceMax(rocket.getDistanceMax());
		dto.setHeightMax(rocket.getHeightMax());
		dto.setName(rocket.getName());
		dto.setSort(rocket.getSort());
		dto.setSpeedMax(rocket.getSpeedMax());
		
		return dto;
	}
	
	public static Rocket toRocket(RocketDto rocketDto) {
		Rocket rocket = new Rocket();
		rocket.setId(rocketDto.getId());
		rocket.setAngleMax(rocketDto.getAngleMax());
		rocket.setAngleMin(rocketDto.getAngleMin());
		rocket.setCreatedAt(rocketDto.getCreatedAt());
		rocket.setDistanceMax(rocketDto.getDistanceMax());
		rocket.setHeightMax(rocketDto.getHeightMax());
		rocket.setName(rocketDto.getName());
		rocket.setSort(rocketDto.getSort());
		rocket.setSpeedMax(rocketDto.getSpeedMax());
		
		return rocket;
	}

	public static WindDirectionDto toWindDirectionDto(WindDirection wd) {
		WindDirectionDto dto = new WindDirectionDto();
		dto.setId(wd.getId());
		dto.setWindDirectionName(wd.getWindDirectionName());
		
		return dto;
	}	
	
	public static WindDirection toWindDirection(WindDirectionDto dto) {
		WindDirection wd = new WindDirection();
		wd.setId(dto.getId());
		wd.setWindDirectionName(dto.getWindDirectionName());
		
		return wd;
	}	
	
}
