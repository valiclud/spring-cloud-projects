package com.valicek.rocket.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valicek.rocket.dto.WindDirectionDto;
import com.valicek.rocket.entity.WindDirection;

@Service
public class WindDirectionService {

	@Autowired
	WindDirectionRepository windDirectionRepo;
	
	public List<WindDirectionDto> findAll(){
		Iterable<WindDirection> dirs = this.windDirectionRepo.findAll();
		List<WindDirectionDto> dirDtos = new ArrayList<>();
		if (dirs.iterator().hasNext()) {
			for (WindDirection wd : dirs) {
				WindDirectionDto dto = new WindDirectionDto();
				dto.setId(wd.getId());
				dto.setWindDirectionName(wd.getWindDirectionName());
				dirDtos.add(dto);
			}
		}
		return dirDtos;
	}

	public List<WindDirectionDto> findByWindDirectionName(String name) {
		Iterable<WindDirection> dirs = this.windDirectionRepo.findByWindDirectionName(name);
		List<WindDirectionDto> dirDtos = new ArrayList<>();
		if (dirs.iterator().hasNext()) {
			for (WindDirection wd : dirs) {
				WindDirectionDto dto = new WindDirectionDto();
				dto.setId(wd.getId());
				dto.setWindDirectionName(wd.getWindDirectionName());
				dirDtos.add(dto);
			}
		}
		return dirDtos;
	}
	
}
