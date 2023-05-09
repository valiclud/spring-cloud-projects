package tacos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.data.OrderService;
import tacos.dto.TacoOrderDto;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrderDto")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value={"", "/", "/current"}, method = RequestMethod.GET)
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping()
	public String processOrder(@Valid TacoOrderDto tacoOrderDto, 
	    Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
		    return "orderForm";
		  }

		log.info("Order submitted:{}", tacoOrderDto);
		sessionStatus.setComplete();
		orderService.save(tacoOrderDto);
		
		return "redirect:/";
	}
	
}
