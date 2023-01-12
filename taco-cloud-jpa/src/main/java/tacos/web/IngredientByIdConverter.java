package tacos.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.Ingredient;
import tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements 
Converter<String, Ingredient> {

	private IngredientRepository ingredientRepo;
	
	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@Override
	public Ingredient convert(String code) {
		List<Ingredient> ingredients = ingredientRepo.findByCode(code);
		if (ingredients != null && !ingredients.isEmpty())
			return ingredientRepo.findByCode(code).get(0);
		else
			return null;
	}

}
