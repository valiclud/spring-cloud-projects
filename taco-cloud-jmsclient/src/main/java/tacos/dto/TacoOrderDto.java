package tacos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class TacoOrderDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6078300839106281658L;

	private Date placedAt = new Date();

	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;

	transient private ClientDto clientDto;

	transient private List<TacoDto> tacos = new ArrayList<>();

	public void addTaco(TacoDto taco) {
		this.tacos.add(taco);
	}

}
