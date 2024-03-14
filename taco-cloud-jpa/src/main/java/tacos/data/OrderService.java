package tacos.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import tacos.dto.TacoOrderDto;
import tacos.entity.Client;
import tacos.entity.TacoOrder;
import tacos.messaging.JmsOrderMessagingService;

@Slf4j
@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ClientRepository clientRepository;
	
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
			setExistingClient(tacoOrder);
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
	
	public void deleteByOrderId(long orderId) throws EmptyResultDataAccessException{
		this.clientRepository.deleteById(orderId);
	}
	
	private void setExistingClient(TacoOrder tacoOrder) {
		List<Client> clients = this.clientRepository.findByDeliveryName(tacoOrder.getClient().getDeliveryName());
		if (isClientAlreadyInDatabase(tacoOrder, clients)) {
			tacoOrder.setClient(clients.get(0));
		} else {
			if ((tacoOrder.getClient() != null) && (tacoOrder.getClient().getId() != null))
				tacoOrder.getClient().setId(null);
		}
	}
	
	private boolean isClientAlreadyInDatabase(TacoOrder tacoOrder, List<Client> clients) {
	  if(!clients.isEmpty() 
	      && tacoOrder.getClient().getDeliveryName().equals(clients.get(0).getDeliveryName())) {
	    return true;
	  } else {
	    return false;
	  }
	}
	
}
