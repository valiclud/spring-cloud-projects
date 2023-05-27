package tacos.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;


@Configuration
public class JmsOrderMessageConverter {

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter mappingJackson2MessageConverter 
             = new MappingJackson2MessageConverter();
        //mappingJackson2MessageConverter.setTargetType(MessageType.OBJECT);
        mappingJackson2MessageConverter.setTypeIdPropertyName("_typeId");
        return mappingJackson2MessageConverter;
    }   
}