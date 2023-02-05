package tacos.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.TacoOrderDto;

@Slf4j
@Component
public class KafkaOrderListener {

	/*
	  private KitchenUI ui;
	 
	  @Autowired
	  public OrderListener(KitchenUI ui) {
	    this.ui = ui;
	  }
	  @KafkaListener(topics="tacocloud.orders.topic")
	  public void handle(String orderDto) {
		  log.info("---RECEIVED ORDER VIA KAFKA MSG:{}" + orderDto);
	   // ui.displayOrder(order);
	  }
	 */
	 
	}

