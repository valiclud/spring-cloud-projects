package com.valicek.rocket.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.valicek.rocket.entity.Rocket;

public interface RocketRepository extends CrudRepository<Rocket, Long> {

	List<Rocket> findByName(String name);
}