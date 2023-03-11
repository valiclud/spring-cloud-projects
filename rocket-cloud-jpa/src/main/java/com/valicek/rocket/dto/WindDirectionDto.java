package com.valicek.rocket.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class WindDirectionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long Id;
	private String windDirectionName;
}
