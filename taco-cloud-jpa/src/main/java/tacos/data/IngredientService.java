package tacos.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.dto.ClientDto;
import tacos.dto.IngredientDto;
import tacos.entity.Client;
import tacos.entity.Ingredient;

@Service
public class IngredientService {

	@Autowired
	IngredientRepository ingredientRepository;
	
	public List<IngredientDto> findAll(){
		Iterable<Ingredient> ingreds = this.ingredientRepository.findAll();
		
		return ingredientsToDto(ingreds);
	}
	
  public Optional<IngredientDto> findById(Long id) {
    if (!Optional.of(id).isEmpty()) {
      Optional<Ingredient> ingredient = this.ingredientRepository.findById(id);
      if (!ingredient.isEmpty())
        return Optional.of(Helper.toIngredientDto(ingredient.get()));
    }
    return Optional.empty();
  }

	
	public List<IngredientDto> findByCode(String code)  {
		Iterable<Ingredient> ingreds = this.ingredientRepository.findByCode(code);
		
		return ingredientsToDto(ingreds);
	}
	
  public Optional<IngredientDto> save(IngredientDto ingredientDto) {
    if (Optional.of(ingredientDto).isPresent()) {
      Ingredient ingredient = Helper.toIngredient(ingredientDto);
      Ingredient ingredientSaved = this.ingredientRepository.save(ingredient);
      if (Optional.of(ingredientSaved).isPresent()) {
        IngredientDto ingredientDtoSaved = Helper.toIngredientDto(ingredientSaved);
        return Optional.of(ingredientDtoSaved);
      }
    }
    return Optional.empty();
  }

	
	private List<IngredientDto> ingredientsToDto(Iterable<Ingredient> ingreds){
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
