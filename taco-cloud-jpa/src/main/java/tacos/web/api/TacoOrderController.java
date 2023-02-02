package tacos.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.data.OrderRepository;
import tacos.data.OrderService;
import tacos.dto.TacoOrderDto;
import tacos.entity.TacoOrder;
import tacos.messaging.OrderMessagingService;

@Slf4j
@RestController
@RequestMapping(path = "/api/tacoorders", produces = "application/json")
@CrossOrigin(origins = {"http://tacocloud:8080", "http://tacocloud.com"})
public class TacoOrderController {

	private OrderService orderService;
	private OrderMessagingService messageService;

	public TacoOrderController(OrderService orderService,
			OrderMessagingService messageService) {
		this.orderService = orderService;
		this.messageService = messageService;
	}

	@GetMapping(params = "recent")
	public List<TacoOrderDto> recentTacoOrders() {
		List<TacoOrderDto> tacoOrders = orderService.findAll();
		return tacoOrders;
	}
/*	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TacoOrderDto> postTaco(@RequestBody TacoOrderDto tacoOrder) {
		Optional<TacoOrderDto> savedTaco = orderService.save(tacoOrder);
		if (savedTaco.isEmpty()) {
			log.info("Taco Order: " + tacoOrder + " was not saved in database");
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		}
		log.info("Taco Order: " + savedTaco + " was sucessfully saved in database");

		return new ResponseEntity<TacoOrderDto>(HttpStatus.OK);
	}
*/	
	@PostMapping(consumes="application/json")
	  @ResponseStatus(HttpStatus.CREATED)
	  public TacoOrderDto postOrder(@RequestBody TacoOrderDto order) {
	    log.info("1----1 " + order);
		messageService.sendOrder(order);
	    Optional<TacoOrderDto> savedTaco = orderService.save(order);
	    return savedTaco.get();
	  }

}
