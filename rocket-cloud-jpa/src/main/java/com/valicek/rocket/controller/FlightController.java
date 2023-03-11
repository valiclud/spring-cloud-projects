package com.valicek.rocket.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.valicek.rocket.data.FlightService;
import com.valicek.rocket.data.RocketService;
import com.valicek.rocket.data.WindDirectionService;
import com.valicek.rocket.dto.FlightDto;
import com.valicek.rocket.dto.RocketDto;
import com.valicek.rocket.dto.WindDirectionDto;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
@SessionAttributes("flightDto")
public class FlightController {
	
	@Autowired
	private WindDirectionService windService;
	@Autowired
	private RocketService rocketService;
	@Autowired
	private FlightService flightService;
	 
	@GetMapping             
	  public String home() {
	    return "home";
	  }
	
	@PostMapping
	public String processFlight(@Valid FlightDto flight, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			log.info("Flight submitted:{}", flight);
		    return "home";
		  }
		setWindDirectionId(flight);
		setRocketProperties(flight);
		
		sessionStatus.setComplete();
		log.info("Flight submitted:{}", flight);
		flightService.save(flight);
		
		return "home";
	}
	
	@ModelAttribute(name = "flightDto")
	public FlightDto rocket() {
		FlightDto f = new FlightDto();
		return f;
	}

	@ModelAttribute("allRockets")
	public List<RocketDto> populateRocketSorts() {
		return rocketService.findAll();
	}

	@ModelAttribute("sortRockets")
	public List<RocketDto>populateAirGroundRockets() {
		Set<String> filteredRockets = new HashSet<>();
		List<RocketDto> rockets = rocketService.findAll();
		rockets.removeIf(r -> !filteredRockets.add(r.getSort()));

		return rockets;
	}
 
	@ModelAttribute("allWindDirections")
	public List<WindDirectionDto>populateWindDirections() {
		return windService.findAll();
	}
	
	private void setWindDirectionId(FlightDto flightDto) {
		List<WindDirectionDto> wd = windService.findByWindDirectionName(flightDto.getWindDirectionDto()
				.getWindDirectionName());
		if (wd != null && !wd.isEmpty()) {
			WindDirectionDto dto = wd.get(0);
			flightDto.getWindDirectionDto().setId(dto.getId());
		}else {
			log.info("Did not find WindDirection for flight: " +flightDto);
		}
	}
	
	private void setRocketProperties(FlightDto flightDto) {
		List<RocketDto> rockets = rocketService.findByName(flightDto.getRocketDto().getName());
		if (rockets != null && !rockets.isEmpty()) {
			RocketDto dto = rockets.get(0);
			flightDto.getRocketDto().setId(dto.getId());
			flightDto.getRocketDto().setSpeedMax(dto.getSpeedMax());
			flightDto.getRocketDto().setHeightMax(dto.getHeightMax());
			flightDto.getRocketDto().setDistanceMax(dto.getDistanceMax());
			flightDto.getRocketDto().setCreatedAt(dto.getCreatedAt());
		} else {
			log.info("Did not find Rocket for flight: " +flightDto);
		}
	}
}
