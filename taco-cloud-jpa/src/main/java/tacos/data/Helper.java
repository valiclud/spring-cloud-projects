package tacos.data;

import java.util.ArrayList;
import java.util.List;

import tacos.dto.IngredientDto;
import tacos.dto.TacoDto;
import tacos.dto.TacoOrderDto;
import tacos.entity.Ingredient;
import tacos.entity.Taco;
import tacos.entity.TacoOrder;

public class Helper {

	private Helper() {
	}
	
	public static IngredientDto toIngredientDto(Ingredient ing) {
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setId(ing.getId());
		ingredientDto.setName(ing.getName());
		ingredientDto.setCode(ing.getCode());
		ingredientDto.setType(IngredientDto.Type.valueOf(ing.getType().name()));
		
		return ingredientDto;
	}
	
	public static Ingredient toIngredient(IngredientDto ingDto) {
		Ingredient ing = new Ingredient(ingDto.getId(),ingDto.getCode(),ingDto.getName(),
				Ingredient.Type.valueOf(ingDto.getType().name()));
		
		return ing;
	}
	
	public static TacoDto toTacoDto(Taco taco) {
		TacoDto tacoDto = new TacoDto();
		tacoDto.setName(taco.getName());
		tacoDto.setCreatedAt(taco.getCreatedAt());
		
		List<Ingredient> ings = taco.getIngredients();
		List<IngredientDto> dtos = new ArrayList<>();
		for(Ingredient ing : ings) {
			dtos.add(Helper.toIngredientDto(ing));
		}
		tacoDto.setIngredients(dtos);
		
		return tacoDto;
	}
	
	public static Taco toTaco(TacoDto tacoDto) {
		Taco taco = new Taco();
		taco.setName(tacoDto.getName());
		taco.setCreatedAt(tacoDto.getCreatedAt());
		
		List<IngredientDto> dtos = tacoDto.getIngredients();
		List<Ingredient> ings = new ArrayList<>();
		for(IngredientDto dto : dtos) {
			ings.add(Helper.toIngredient(dto));
		}
		taco.setIngredients(ings);
		
		return taco;
	}
	
	public static TacoOrderDto toTacoOrderDto(TacoOrder tacoOrder) {
		TacoOrderDto tacoOrderDto = new TacoOrderDto();
		tacoOrderDto.setDeliveryName(tacoOrder.getDeliveryName());
		tacoOrderDto.setDeliveryStreet(tacoOrder.getDeliveryStreet());
		tacoOrderDto.setDeliveryCity(tacoOrder.getDeliveryCity());
		tacoOrderDto.setDeliveryState(tacoOrder.getDeliveryState());
		tacoOrderDto.setDeliveryZip(tacoOrder.getDeliveryZip());
		tacoOrderDto.setCcNumber(tacoOrder.getCcNumber());
		tacoOrderDto.setCcExpiration(tacoOrder.getCcExpiration());
		tacoOrderDto.setCcCVV(tacoOrder.getCcCVV());
		
		List<Taco> tacos = tacoOrder.getTacos();
		List<TacoDto> dtos = new ArrayList<>();
		for(Taco t : tacos) {
			dtos.add(Helper.toTacoDto(t));
		}
		tacoOrderDto.setTacos(dtos);
		
		return tacoOrderDto;
	}
	
	public static TacoOrder toTacoOrder(TacoOrderDto tacoOrderDto) {
		TacoOrder taco = new TacoOrder();
		taco.setDeliveryName(tacoOrderDto.getDeliveryName());
		taco.setDeliveryStreet(tacoOrderDto.getDeliveryStreet());
		taco.setDeliveryCity(tacoOrderDto.getDeliveryCity());
		taco.setDeliveryState(tacoOrderDto.getDeliveryState());
		taco.setDeliveryZip(tacoOrderDto.getDeliveryZip());
		taco.setCcNumber(tacoOrderDto.getCcNumber());
		taco.setCcExpiration(tacoOrderDto.getCcExpiration());
		taco.setCcCVV(tacoOrderDto.getCcCVV());
		List<TacoDto> dtos = tacoOrderDto.getTacos();
		List<Taco> tacos = new ArrayList<>();
		for(TacoDto dto : dtos) {
			tacos.add(Helper.toTaco(dto));
		}
		taco.setTacos(tacos);
		
		return taco;
	}
}
