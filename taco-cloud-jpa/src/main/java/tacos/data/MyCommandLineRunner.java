package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

	private IngredientRepository repo;

	@Autowired
	public  MyCommandLineRunner(IngredientRepository repo) {
		this.repo = repo;
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		repo.save(new Ingredient(Long.valueOf(1), "FLTO", "Flour Tortilla", Type.WRAP));
	    repo.save(new Ingredient(Long.valueOf(2), "COTO", "Corn Tortilla", Type.WRAP));
	    repo.save(new Ingredient(Long.valueOf(3), "GRBF", "Ground Beef", Type.PROTEIN));
	    repo.save(new Ingredient(Long.valueOf(4), "CARN", "Carnitas", Type.PROTEIN));
	    repo.save(new Ingredient(Long.valueOf(5), "TMTO", "Diced Tomatoes", Type.VEGGIES));
	    repo.save(new Ingredient(Long.valueOf(6), "LETC", "Lettuce", Type.VEGGIES));
	    repo.save(new Ingredient(Long.valueOf(7), "CHED", "Cheddar", Type.CHEESE));
	    repo.save(new Ingredient(Long.valueOf(8), "JACK", "Monterrey Jack", Type.CHEESE));
	    repo.save(new Ingredient(Long.valueOf(9), "SLSA", "Salsa", Type.SAUCE));
	    repo.save(new Ingredient(Long.valueOf(10), "SRCR", "Sour Cream", Type.SAUCE));

	}
}
