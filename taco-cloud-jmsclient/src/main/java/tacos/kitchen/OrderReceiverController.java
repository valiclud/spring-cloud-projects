package tacos.kitchen;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tacos.dto.TacoOrderDto;

@Slf4j
@Profile({"jms-template", "rabbitmq-template"})
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiverController {

  private final OrderReceiver orderReceiver;

  @GetMapping("/receive")
  public String receiveOrder(Model model) {
    TacoOrderDto order = orderReceiver.receiveOrder();
    if (order != null) {
      model.addAttribute("order", order);
      return "receiveOrder";
    }
    return "noOrder";
  }


}