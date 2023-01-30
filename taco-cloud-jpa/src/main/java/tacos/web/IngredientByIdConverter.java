package tacos.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.data.IngredientService;
import tacos.dto.IngredientDto;

@Component
public class IngredientByIdConverter implements 
Converter<String, IngredientDto> {

	private final IngredientService ingredientService;
	
	@Autowired
	public IngredientByIdConverter(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
	
	@Override
	public IngredientDto convert(String code) {
		List<IngredientDto> ingredients = ingredientService.findByCode(code);
		if (ingredients != null && !ingredients.isEmpty())
			return ingredients.get(0);
		else
			return null;
	}

}
