package com.valicek.rocket.data;

import org.springframework.data.repository.CrudRepository;

import com.valicek.rocket.entity.Flight;

public interface FlightRepository  extends CrudRepository<Flight, Long> {

}
