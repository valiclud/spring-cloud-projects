package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import tacos.dto.TacoOrderDto;

@Primary
@Service
public class KafkaOrderMessagingService implements OrderMessagingService {
	  
	  private KafkaTemplate<String, TacoOrderDto> kafkaTemplate;
	  
	  @Autowired
	  public KafkaOrderMessagingService(
	          KafkaTemplate<String, TacoOrderDto> kafkaTemplate) {
	    this.kafkaTemplate = kafkaTemplate;
	  }

	  @Override
	  public void sendOrder(TacoOrderDto orderDto) {
	   // kafkaTemplate.sendDefault(orderDto);
	    kafkaTemplate.send("tacocloud.orders.topic", orderDto);
	  }
	  
	}

