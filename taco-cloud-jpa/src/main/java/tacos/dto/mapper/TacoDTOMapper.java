package tacos.dto.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.dto.TacoDto;
import tacos.entity.Taco;

@Service
public class TacoDTOMapper implements Function<Taco, TacoDto> {

	@Autowired
	IngredientDTOMapper ingredientDTOMapper;
	
	@Override
	public TacoDto apply(Taco taco) {
		return new TacoDto(
				taco.getId(),
				taco.getName(),
				taco.getCreatedAt(),
				taco.getIngredients()
				.stream()
				.map(ingredientDTOMapper)
				.toList());
	}
}