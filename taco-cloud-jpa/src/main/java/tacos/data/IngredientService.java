package tacos.data;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
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

	public IngredientDto save(IngredientDto ingredientDto) {
		if (!this.ingredientRepository.existsById(ingredientDto.getId())) {
			Ingredient ingredient = this.ingredientRepository.save(dtoIngredientMapper.apply(ingredientDto));
			return ingredientDTOMapper.apply(ingredient);
		}
		throw new ResourceAccessException("Ingredient with id [%s] already exists ".formatted(ingredientDto.getId()));
	}

	public void deleteById(long id) {
		if (!this.ingredientRepository.existsById(id)) {
			throw new ResourceNotFoundException("Ingredient with id [%s] not found ".formatted(id));
		}
		this.ingredientRepository.deleteById(id);
	}
	
	public IngredientDto updateIngredientByFields(Long id, Map<String, Object> fields) {
		if (!this.ingredientRepository.existsById(id)) {
			throw new ResourceNotFoundException("Ingredient with id [%s] not found ".formatted(id));
		}
		Optional<Ingredient> existingIngredient = this.ingredientRepository.findById(id);
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Ingredient.class, key);
			field.setAccessible(Boolean.TRUE);
			Class<?> type = field.getType();
			if (type.isEnum()) {
				ReflectionUtils.setField(field, existingIngredient.get(), Type.valueOf((String) value));
			} else if (type.isAssignableFrom(Long.class)) {
				ReflectionUtils.setField(field, existingIngredient.get(), ((Number)value).longValue());
			} else {
				ReflectionUtils.setField(field, existingIngredient.get(), value);
			}
		});
		return ingredientDTOMapper.apply(this.ingredientRepository.save(existingIngredient.get()));
	}
}
