package com.valicek.rocket.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.valicek.rocket.entity.WindDirection;

public interface WindDirectionRepository extends CrudRepository<WindDirection, Long> {
	
	List<WindDirection> findByWindDirectionName(String name);
}