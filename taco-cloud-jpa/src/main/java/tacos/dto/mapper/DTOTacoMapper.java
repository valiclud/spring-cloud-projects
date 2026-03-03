package tacos.dto.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.dto.TacoDto;
import tacos.entity.Taco;

@Service
public class DTOTacoMapper implements Function<TacoDto, Taco> {

	@Autowired
	DTOIngredientMapper dTOIngredientMapper;
	
	@Override
	public Taco apply(TacoDto taco) {
		return new Taco(
				taco.getId(),
				taco.getName(),
				taco.getCreatedAt(),
				taco.getIngredients()
				.stream()
				.map(dTOIngredientMapper)
				.toList());
	}
}