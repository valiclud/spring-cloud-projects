package tacos.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tacos.dto.IngredientDto;
import tacos.entity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

	@Query("SELECT new tacos.dto.IngredientDto(i.id, i.code, i.name, i.type) "
			+ "FROM Ingredient i WHERE i.code = :code")
	List<IngredientDto> findIngredientDtoByCode(String code);

}