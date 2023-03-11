package com.valicek.rocket.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valicek.rocket.dto.FlightDto;
import com.valicek.rocket.dto.Helper;
import com.valicek.rocket.entity.Flight;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlightService {
	
	@Autowired
	FlightRepository flightRepository;
	
	public List<FlightDto> findAll(){
		Iterable<Flight> flights = this.flightRepository.findAll();
		List<FlightDto> flightDtos = new ArrayList<>();
		if (flights.iterator().hasNext()) {
			for (Flight flight : flights) {
				FlightDto dto = Helper.toFlightDto(flight);
				flightDtos.add(dto);
			}
		}
		return flightDtos;
	}
	
	public Optional<FlightDto> save(FlightDto flightDto) {
		if (Optional.of(flightDto).isPresent()) {
			Flight flight = Helper.toFlight(flightDto);
			Flight flightSaved = this.flightRepository.save(flight);
			if (Optional.of(flightSaved).isPresent()) {
				FlightDto flightDtoSaved = Helper.toFlightDto(flightSaved);
				return Optional.of(flightDtoSaved);
			}
		}
		return Optional.empty();
	}

}
