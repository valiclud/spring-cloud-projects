package tacos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import tacos.data.IngredientService;
import tacos.dto.IngredientDto;
import tacos.dto.TacoDto;
import tacos.dto.TacoOrderDto;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrderDto")
public class DesignTacoController {

	private final IngredientService ingredientService;

	@Autowired
	public DesignTacoController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<IngredientDto> ingredsList = ingredientService.findAll();
		IngredientDto.Type[] types = IngredientDto.Type.values();
		for (IngredientDto.Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredsList, type));
		}
	}

	@ModelAttribute(name = "tacoOrderDto")
	public TacoOrderDto order() {
		return new TacoOrderDto();
	}

	@ModelAttribute(name = "tacoDto")
	public TacoDto taco() {
		return new TacoDto();
	}

	@GetMapping
	public String showDesignForm() {
		return "design";
	}

	@PostMapping
	public String processTaco(@Valid TacoDto taco, Errors errors, 
			@ModelAttribute TacoOrderDto tacoOrder) {

		if (errors.hasErrors()) {
			return "design";
		}

		tacoOrder.addTaco(taco);

		return "redirect:/orders/current";
	}

	private Iterable<IngredientDto> filterByType(List<IngredientDto> ingredients, IngredientDto.Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}

}
