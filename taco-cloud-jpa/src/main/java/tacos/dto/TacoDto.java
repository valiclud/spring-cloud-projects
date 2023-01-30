package tacos.dto;

import java.beans.JavaBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.ManagedBean;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tacos.entity.Ingredient;

@Data
public class TacoDto {

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
