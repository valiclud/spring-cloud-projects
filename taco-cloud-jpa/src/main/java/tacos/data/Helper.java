package tacos.data;

import java.util.ArrayList;
import java.util.List;

import tacos.dto.ClientDto;
import tacos.dto.IngredientDto;
import tacos.dto.TacoDto;
import tacos.dto.TacoOrderDto;
import tacos.entity.Client;
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
		tacoOrderDto.setCcNumber(tacoOrder.getCcNumber());
		tacoOrderDto.setCcCVV(tacoOrder.getCcCVV());
		tacoOrderDto.setCcExpiration(tacoOrder.getCcExpiration());
		tacoOrderDto.setPlacedAt(tacoOrder.getPlacedAt());
		tacoOrderDto.setClientDto(toClientDto(tacoOrder.getClient()));
		
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
		taco.setCcNumber(tacoOrderDto.getCcNumber());
		taco.setCcCVV(tacoOrderDto.getCcCVV());
		taco.setCcExpiration(tacoOrderDto.getCcExpiration());
		taco.setPlacedAt(tacoOrderDto.getPlacedAt());
		Client c = toClient(tacoOrderDto.getClientDto());
		taco.setClient(c);
		List<TacoDto> dtos = tacoOrderDto.getTacos();
		List<Taco> tacos = new ArrayList<>();
		for(TacoDto dto : dtos) {
			tacos.add(Helper.toTaco(dto));
		}
		taco.setTacos(tacos);
		
		return taco;
	}
	
	public static Client toClient(ClientDto clientDto) {
	  Client client = new Client();
	  client.setId(clientDto.getId());
	  client.setDeliveryName(clientDto.getDeliveryName());
	  client.setDeliveryStreet(clientDto.getDeliveryStreet());
	  client.setDeliveryCity(clientDto.getDeliveryCity());
    client.setDeliveryState(clientDto.getDeliveryState());
    client.setDeliveryZip(clientDto.getDeliveryZip());
    client.setCreatedAt(clientDto.getCreatedAt());
	  
	  return client;
	}
	
	 public static ClientDto toClientDto(Client client) {
	    ClientDto clientDto = new ClientDto();
	    clientDto.setId(client.getId());
	    clientDto.setDeliveryName(client.getDeliveryName());
	    clientDto.setDeliveryStreet(client.getDeliveryStreet());
	    clientDto.setDeliveryCity(client.getDeliveryCity());
	    clientDto.setDeliveryState(client.getDeliveryState());
	    clientDto.setDeliveryZip(client.getDeliveryZip());
	    clientDto.setCreatedAt(client.getCreatedAt());
	    
	    return clientDto;
	  }
}
