package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import tacos.dto.TacoOrderDto;

@Service
@Primary
public class JmsOrderMessagingService implements OrderMessagingService {

	private JmsTemplate jms;
	 
	  @Autowired
	  public JmsOrderMessagingService(JmsTemplate jms) {
	    this.jms = jms;
	  }
	  
	  @Override
	  public void sendOrder(TacoOrderDto orderDto) {
	    //jms.send(session -> session.createObjectMessage(orderDto));
		  jms.convertAndSend("tacocloud.order.queue", orderDto);
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

