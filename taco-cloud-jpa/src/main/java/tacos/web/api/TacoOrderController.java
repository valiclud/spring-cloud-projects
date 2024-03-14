package tacos.web.api;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.data.OrderService;
import tacos.dto.TacoOrderDto;
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
*/
    @GetMapping(params = "recent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> recentTacoOrders() {
	List<TacoOrderDto> tacoOrders = orderService.findAll();
	  /*
	  	List<JSONObject> entities = new ArrayList<JSONObject>();
	  	entities.addAll(tacoOrders);

		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("total", 1234);
		map.put("total_pages", 124);
		map.put("per_page", 10);
		map.put("page", 1);
		map.put("data", tacoOrders);
		HttpHeaders headers = new HttpHeaders();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValueAsString();
		*/

		return new ResponseEntity<>(tacoOrders,HttpStatus.CREATED);
}
	@GetMapping(params = "clients")
    public List<TacoOrderDto> clientsTacoOrders() {
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
		//messageService.sendOrder(order);
	    Optional<TacoOrderDto> savedTaco = orderService.save(order);
	    return savedTaco.get();
	  }
	
		@DeleteMapping("/{orderId}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deleteOrder(@PathVariable("orderId") Long orderId) {
			try {
				orderService.deleteByOrderId(orderId);
			} catch (EmptyResultDataAccessException ex) {
				log.error(ex.toString());
			}
		}

}
