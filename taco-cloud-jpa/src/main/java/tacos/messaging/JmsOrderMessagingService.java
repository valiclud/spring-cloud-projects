package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.TacoOrderDto;

@Slf4j
@Service
@Primary
public class JmsOrderMessagingService implements OrderMessagingService {

	private JmsTemplate jms;
    private MessageConverter messageConverter;
	 
	  @Autowired
	  public JmsOrderMessagingService(JmsTemplate jms, MessageConverter messageConverter) {
	    this.jms = jms;
	    this.messageConverter = messageConverter;
	  }
	  
	  @Override
	  public void sendOrder(TacoOrderDto order) {
		  log.info("------------- SENDING JMS ORDER-------------- " + order);
		  jms.setMessageConverter(messageConverter);
		  jms.convertAndSend("tacocloud.order.queue", order);
	  }
	  
	/* 
	  @Override
	  public void sendOrder(TacoOrderDto orderDto) {
	    jms.send(new MessageCreator() {
	                @Override
	                public Message createMessage(Session session)
	                                              throws JMSException {
	                  return session.createObjectMessage(orderDto);

	                }
        }
);
}
	*/  
}

