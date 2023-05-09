package tacos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TacoDto implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  @NotNull
	  @Size(min=5, message="Name must be at least 5 characters long")
	  private String name;
	 
	  private Date createdAt = new Date();
	 
	  @Size(min=1, message="You must choose at least 1 ingredient")
	  private List<IngredientDto> ingredients = new ArrayList<>();
	  
	  public void addIngredient(IngredientDto ingredient) {
	    this.ingredients.add(ingredient);
	  }
	
}
