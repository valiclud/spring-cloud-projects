package com.valicek.rocket.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valicek.rocket.dto.Helper;
import com.valicek.rocket.dto.RocketDto;
import com.valicek.rocket.entity.Rocket;

@Service
public class RocketService {

	@Autowired
	RocketRepository rocketRepo;
	
	public List<RocketDto> findAll(){
		Iterable<Rocket> rockets = this.rocketRepo.findAll();
		List<RocketDto> listDtos = new ArrayList<>();
		if (rockets.iterator().hasNext()) {
			for (Rocket rocket : rockets) {
				RocketDto dto = Helper.toRocketDto(rocket);
				listDtos.add(dto);
			}
		}
		return listDtos;
	}
	
	public List<RocketDto> findByName(String name){
		Iterable<Rocket> rockets = this.rocketRepo.findByName(name);
		List<RocketDto> listDtos = new ArrayList<>();
		if (rockets.iterator().hasNext()) {
			for (Rocket rocket : rockets) {
				RocketDto dto = Helper.toRocketDto(rocket);
				listDtos.add(dto);
			}
		}
		return listDtos;
	}
}
