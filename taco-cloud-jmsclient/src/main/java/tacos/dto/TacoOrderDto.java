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
	private static final long serialVersionUID = -1043787318853132582L;

	private Date placedAt = new Date();

	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;

	private ClientDto clientDto;

	private List<TacoDto> tacos = new ArrayList<>();

	public void addTaco(TacoDto taco) {
		this.tacos.add(taco);
	}

}
