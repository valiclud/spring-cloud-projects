package tacos.kitchen.messaging.jms;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.TacoOrderDto;
import tacos.kitchen.OrderReceiver;

@Slf4j
@Profile("jms-template")
@Component
public class JmsOrderReceiver implements OrderReceiver {

  private JmsTemplate jms;

  public JmsOrderReceiver(JmsTemplate jms) {
    this.jms = jms;
  }

  @Override
  public TacoOrderDto receiveOrder() {
    return (TacoOrderDto) jms.receiveAndConvert("tacocloud.order.queue");
  }

}