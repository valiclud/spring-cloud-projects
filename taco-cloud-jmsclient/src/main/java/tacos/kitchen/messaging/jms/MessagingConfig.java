package tacos.kitchen.messaging.jms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.TacoOrderDto;
import tacos.kitchen.messaging.jms.listener.OrderListener;

//@Profile({"jms-template", "jms-listener"})
@Configuration
@EnableJms
@Slf4j
public class MessagingConfig {

  @Bean
  public MappingJackson2MessageConverter messageConverter() {
    MappingJackson2MessageConverter messageConverter =
                            new MappingJackson2MessageConverter();
    messageConverter.setTypeIdPropertyName("_typeId");

    Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
    typeIdMappings.put("order", TacoOrderDto.class);
    messageConverter.setTypeIdMappings(typeIdMappings);

    return messageConverter;
  }
  
}