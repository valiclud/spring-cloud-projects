package tacos.dto;

import lombok.Data;

@Data
public class IngredientDto {

	  private Long id;
	  private String code;
	  private String name;
	  private Type type;
	  public enum Type {
	    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	  }
}
