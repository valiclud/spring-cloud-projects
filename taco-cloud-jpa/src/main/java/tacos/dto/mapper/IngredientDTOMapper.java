package tacos.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import tacos.dto.IngredientDto;
import tacos.entity.Ingredient;

@Service
public class IngredientDTOMapper implements Function<Ingredient, IngredientDto> {

	@Override
	public IngredientDto apply(Ingredient ingredient) {
		return new IngredientDto(
				ingredient.getId(),
				ingredient.getCode(),
				ingredient.getName(),
				ingredient.getType());
	}

}