package com.valicek.rocket.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double speed;
	private Integer distance;
	private Integer height;
	private Double angle;
	private Double windSpeed;
	private Date createdAt;
	
	@ManyToOne()
	@JoinColumn(name="fk_wind_direction_id")
	private WindDirection windDirection;
	
		
	@ManyToOne()
	@JoinColumn(name="fk_rocket_id")
	private Rocket rocket;
	
}
