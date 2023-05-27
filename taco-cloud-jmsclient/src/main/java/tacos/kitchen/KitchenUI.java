package tacos.kitchen;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.TacoOrderDto;

@Component
@Slf4j
public class KitchenUI {

  public void displayOrder(TacoOrderDto order) {
    log.info("RECEIVED ORDER:  " + order);
  }
  
}