package com.valicek.rocket.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WindDirection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String windDirectionName;

	public WindDirection(Long id, String windDirectionName) {
		super();
		this.id = id;
		this.windDirectionName = windDirectionName;
	}
	
	@OneToMany(mappedBy="windDirection")
    private List<Flight> flights;

}
