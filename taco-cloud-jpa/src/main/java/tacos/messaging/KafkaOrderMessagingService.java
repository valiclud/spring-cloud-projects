package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import tacos.dto.TacoOrderDto;

//@Primary
@Service
public class KafkaOrderMessagingService implements OrderMessagingService {
	  
	  private KafkaTemplate<String, String> kafkaTemplate;
	  
	  @Autowired
	  public KafkaOrderMessagingService(
	          KafkaTemplate<String, String> kafkaTemplate) {
	    this.kafkaTemplate = kafkaTemplate;
	  }

	  @Override
	  public void sendOrder(TacoOrderDto orderDto) {
	    kafkaTemplate.sendDefault(orderDto.toString());
	   // kafkaTemplate.send("tacocloud.orders.topic", orderDto);
	  }
	  
	}

