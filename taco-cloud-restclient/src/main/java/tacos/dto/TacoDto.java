package tacos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class TacoDto implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private String name;
	  private Date createdAt = new Date();
	  private List<IngredientDto> ingredients = new ArrayList<>();
	  
	  public void addIngredient(IngredientDto ingredient) {
	    this.ingredients.add(ingredient);
	  }
	
}
