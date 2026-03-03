package tacos.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import tacos.dto.IngredientDto;
import tacos.entity.Ingredient;

@Service
public class DTOIngredientMapper implements Function<IngredientDto, Ingredient> {

	@Override
	public Ingredient apply(IngredientDto ingredient) {
		return new Ingredient(
				ingredient.getId(),
				ingredient.getCode(),
				ingredient.getName(),
				ingredient.getType());
	}

}