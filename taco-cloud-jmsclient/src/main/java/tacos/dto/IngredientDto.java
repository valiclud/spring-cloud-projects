package tacos.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force=true)
public class IngredientDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4233834677260047218L;
	
	  private Long id;
	  private String code;
	  private String name;
	  private Type type;
	  public enum Type {
	    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	  }
}
