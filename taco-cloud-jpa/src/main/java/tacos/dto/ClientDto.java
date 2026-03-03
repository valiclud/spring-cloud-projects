package tacos.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tacos.entity.TacoOrder;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ClientDto implements Serializable {

	
	private static final long serialVersionUID = -8854490958833450393L;

	private Long Id;
  
	private Date createdAt = new Date();

	@NotBlank(message = "Delivery name is required")
	private String deliveryName;
	@NotBlank(message = "Delivery street is required")
	private String deliveryStreet;
	@NotBlank(message = "Delivery city is required")
	private String deliveryCity;
	@NotBlank(message = "Delivery state is required")
	private String deliveryState;
	@NotBlank(message = "ZIP code is required")
	private String deliveryZip;

	//private List<TacoOrderDto> tacoOrders;
}