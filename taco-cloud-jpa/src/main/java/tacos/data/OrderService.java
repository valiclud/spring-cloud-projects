package tacos.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.IngredientDto;
import tacos.dto.TacoDto;
import tacos.dto.TacoOrderDto;
import tacos.entity.Ingredient;
import tacos.entity.Taco;
import tacos.entity.TacoOrder;

@Slf4j
@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public List<TacoOrderDto> findAll(){
		Iterable<TacoOrder> tacos = this.orderRepository.findAll();
		List<TacoOrderDto> listTacos = new ArrayList<>();
		if (tacos.iterator().hasNext()) {
			for (TacoOrder tacoOrder : tacos) {
				TacoOrderDto dto = Helper.toTacoOrderDto(tacoOrder);
				listTacos.add(dto);
			}
		}
		return listTacos;
	}
	
	public Optional<TacoOrderDto> save(TacoOrderDto tacoOrderDto) {
		if (Optional.of(tacoOrderDto).isPresent()) {
			TacoOrder tacoOrder = Helper.toTacoOrder(tacoOrderDto);
			TacoOrder tacoOrderSaved = this.orderRepository.save(tacoOrder);
			if (Optional.of(tacoOrderSaved).isPresent()) {
				TacoOrderDto tacoOrderDtoSaved = Helper.toTacoOrderDto(tacoOrderSaved);
				return Optional.of(tacoOrderDtoSaved);
			}
		}
		return Optional.empty();
	}
	
	public Optional<TacoOrderDto> find(Long id) {
		Optional<TacoOrder> opt = this.orderRepository.findById(id);
		if (opt.isPresent()) {
			TacoOrderDto tacoOrderDto = Helper.toTacoOrderDto(opt.get());
			return Optional.of(tacoOrderDto);
		} else {
			return Optional.empty();
		}
	}
	
}
