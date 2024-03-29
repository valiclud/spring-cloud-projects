package tacos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.annotation.RestResource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;


@Data
@Entity
@RestResource(rel="tacos", path="tacos")
public class Taco {
 
 
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
 
  private String name;
 
  private Date createdAt = new Date();
 
  @ManyToMany()
  @JoinTable(name="Ingredient_Ref")
  private List<Ingredient> ingredients = new ArrayList<>();
  
  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient);
  }
 
}