package com.valicek.rocket.entity;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Rocket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String sort;
	private Double speedMax;
	private Double angleMin;
	private Double angleMax;
	private Double heightMax;
	private Integer distanceMax;
	private Date createdAt;
	
	public Rocket(Long id, String name, String sort, Double speedMax, Double angleMin, Double angleMax,
			Double heightMax, Integer distanceMax, Date createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.sort = sort;
		this.speedMax = speedMax;
		this.angleMin = angleMin;
		this.angleMax = angleMax;
		this.heightMax = heightMax;
		this.distanceMax = distanceMax;
		this.createdAt = createdAt;
	}

	@OneToMany(mappedBy="rocket")
    private List<Flight> flights;
}
