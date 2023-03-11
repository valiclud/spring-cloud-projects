package com.valicek.rocket.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RocketDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long Id;
	private String name;
	private String sort;
	private Double speedMax;
	private Double angleMin;
	private Double angleMax;
	private Double heightMax;
	private Integer distanceMax;
	private Date createdAt;
}
