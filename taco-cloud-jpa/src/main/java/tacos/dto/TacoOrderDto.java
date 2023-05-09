package tacos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TacoOrderDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date placedAt = new Date();
	
	@NotNull
  @Valid
	private ClientDto clientDto;
	
	private List<TacoDto> tacos = new ArrayList<>();
	
	public void addTaco(TacoDto taco) {
		this.tacos.add(taco);
	}
	
}
