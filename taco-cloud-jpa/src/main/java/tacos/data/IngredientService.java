package tacos.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.IngredientDto;
import tacos.entity.Ingredient;

@Slf4j
@Service
public class IngredientService {

	@Autowired
	IngredientRepository ingredientRepository;
	
	public List<IngredientDto> findAll(){
		Iterable<Ingredient> ingreds = this.ingredientRepository.findAll();
		
		return convertIterableToDto(ingreds);
	}
	
	public List<IngredientDto> findByCode(String code)  {
		Iterable<Ingredient> ingreds = this.ingredientRepository.findByCode(code);
		
		return convertIterableToDto(ingreds);
	}
	
	private List<IngredientDto> convertIterableToDto(Iterable<Ingredient> ingreds){
		List<IngredientDto> listIngreds = new ArrayList<>();
		if (ingreds.iterator().hasNext()) {
			for (Ingredient ingredient : ingreds) {
				IngredientDto dto = Helper.toIngredientDto(ingredient);
				listIngreds.add(dto);
			}
		}
		return listIngreds;
	}
	
}
