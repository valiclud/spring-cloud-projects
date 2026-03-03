package tacos.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.data.IngredientService;
import tacos.dto.IngredientDto;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = " * ")
public class IngredientController {

	private IngredientService service;

	@Autowired
	public IngredientController(IngredientService service) {
		this.service = service;
	}

	@GetMapping
	public List<IngredientDto> allIngredients() {
		return service.findAll();
	}

	/*
	 * @GetMapping public Map<String, List<IngredientDto>> allIngredients() { return
	 * Collections.singletonMap("ingredients", service.findAll()); }
	 */

	@GetMapping("/{id}")
	public ResponseEntity<IngredientDto> clientById(@PathVariable("id") Long id) {
		IngredientDto optIngredient = this.service.findById(id);
		return new ResponseEntity<>(optIngredient, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> postIngredient(@RequestBody IngredientDto ingredientDto) {
		this.service.save(ingredientDto);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
