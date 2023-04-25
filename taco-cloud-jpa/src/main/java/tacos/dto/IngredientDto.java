package tacos.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class IngredientDto implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  private Long id;
	  private String code;
	  private String name;
	  private Type type;
	  public enum Type {
	    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	  }
}
