package tacos.messaging;

import org.springframework.stereotype.Service;

import tacos.dto.TacoOrderDto;

public interface OrderMessagingService {

	public void sendOrder(TacoOrderDto orderDto);
	
}
