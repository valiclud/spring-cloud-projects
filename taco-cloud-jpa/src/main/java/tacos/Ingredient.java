package tacos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient {

	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String code;
	  private String name;
	  private Type type;
	  public enum Type {
	    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	  }

	
}
