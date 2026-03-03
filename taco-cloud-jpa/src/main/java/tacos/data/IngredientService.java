package tacos.data;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import tacos.dto.IngredientDto;
import tacos.dto.mapper.DTOIngredientMapper;
import tacos.dto.mapper.IngredientDTOMapper;
import tacos.entity.Ingredient;

@Service
public class IngredientService {

	@Autowired
	IngredientRepository ingredientRepository;

	@Autowired
	IngredientDTOMapper ingredientDTOMapper;

	@Autowired
	DTOIngredientMapper dtoIngredientMapper;

	public List<IngredientDto> findAll() {
		Iterable<Ingredient> ingredients = this.ingredientRepository.findAll();

		return StreamSupport.stream(ingredients.spliterator(), false).map(ingredientDTOMapper).toList();
	}

	public IngredientDto findById(Long id) {
		return this.ingredientRepository.findById(id).map(ingredientDTOMapper)
				.orElseThrow(() -> new ResourceNotFoundException("Ingredient with id [%s] not found ".formatted(id)));
	}

	public List<IngredientDto> findByCode(String code) {
		return this.ingredientRepository.findIngredientDtoByCode(code);
	}

	public void save(IngredientDto ingredientDto) {
		if (!this.ingredientRepository.existsById(ingredientDto.getId())) {
			this.ingredientRepository.save(dtoIngredientMapper.apply(ingredientDto));
		}
		throw new ResourceAccessException("Ingredient with id [%s] already exists ".formatted(ingredientDto.getId()));
	}

}
