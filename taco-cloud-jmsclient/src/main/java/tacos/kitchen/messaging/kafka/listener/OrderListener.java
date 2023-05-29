package tacos.kitchen.messaging.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.TacoOrderDto;
import tacos.kitchen.KitchenUI;

@Profile("kafka-listener")
@Component
@Slf4j
public class OrderListener {

  private KitchenUI ui;

  @Autowired
  public OrderListener(KitchenUI ui) {
    this.ui = ui;
  }

	@KafkaListener(topics = "tacocloud.orders.topic")
	public void handle(TacoOrderDto tacoOrderDto) {
		ui.displayOrder((TacoOrderDto) tacoOrderDto);
	}
	  /*
  @KafkaListener(topics="tacocloud.orders.topic")
  public void handle(
		Object tacoOrderDto, ConsumerRecord<String, Object> record) {
        log.info("Received from partition {} with timestamp {}",
        record.partition(), record.timestamp());

    ui.displayOrder((TacoOrderDto) tacoOrderDto);
  }
*/
//
// Alternate implementation
//
 /* 
  @KafkaListener(topics="tacocloud.orders.topic")
  public void handle(TacoOrderDto tacoOrderDto, Message<TacoOrderDto> message) {
    MessageHeaders headers = message.getHeaders();
    log.info("Received from partition {} with timestamp {}",
        headers.get(KafkaHeaders.RECEIVED_PARTITION),
        headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));
    ui.displayOrder(tacoOrderDto);
  }
  */

}