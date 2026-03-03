package tacos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tacos.entity.TacoOrder;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class TacoOrderDto implements Serializable{

	private static final long serialVersionUID = -6078300839106281658L;

	private Long id;
	
	private Date placedAt = new Date();
	
	//@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	@Pattern(regexp = "^(0[1-9]|1[0-2])(\\/)([2-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccExpiration;
	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String ccCvv;
	
	//@NotNull
	//@Valid
	private ClientDto clientDto;
	
	private List<TacoDto> tacos = new ArrayList<>();
	
	public void addTaco(TacoDto taco) {
		if (tacos == null)
			tacos = new ArrayList<>();
		this.tacos.add(taco);
	}
	
}