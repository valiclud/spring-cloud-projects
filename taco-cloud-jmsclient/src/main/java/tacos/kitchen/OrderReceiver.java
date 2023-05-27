package tacos.kitchen;

import tacos.dto.TacoOrderDto;

public interface OrderReceiver {

  TacoOrderDto receiveOrder();

}