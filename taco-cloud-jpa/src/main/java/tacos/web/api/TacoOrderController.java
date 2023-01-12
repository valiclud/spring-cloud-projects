package tacos.web.api;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.TacoOrder;
import tacos.data.OrderRepository;

@Slf4j
@RestController
@RequestMapping(path = "/api/tacoorders", produces = "application/json")
//@CrossOrigin(origins = {"http://tacocloud:8080", "http://tacocloud.com"})
//@CrossOrigin(origins="http://localhost:8080")

public class TacoOrderController {

	private OrderRepository orderRepo;

	public TacoOrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping(params = "recent")
	public Iterable<TacoOrder> recentTacoOrders() {
		Iterable<TacoOrder> tacoOrders = orderRepo.findAll();
		log.info("Ordered tacos submitted:{}", tacoOrders);
		return tacoOrders;
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TacoOrder> postTaco(@RequestBody TacoOrder tacoOrder) {
		if (isTacoOrderAlreadySaved(tacoOrder))
			return new ResponseEntity<>(tacoOrder, HttpStatus.NOT_ACCEPTABLE);

		TacoOrder savedTaco = orderRepo.save(tacoOrder);
		if (savedTaco == null) {
			log.info("Taco Order id=" + tacoOrder.getId() + " was not saved in database");
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		}
		log.info("Taco Order id=" + savedTaco.getId() + " delivery name=" + savedTaco.getDeliveryName()
				+ " was sucessfully saved in database");

		return new ResponseEntity<>(savedTaco, HttpStatus.OK);
	}
	
	private boolean isTacoOrderAlreadySaved(TacoOrder tacoOrder) {
		if (tacoOrder.getId() != null) {
			Optional<TacoOrder> t = orderRepo.findById(tacoOrder.getId());
			if (t.isPresent()) {
				log.info("Taco Order id=" + t.get().getId() + " delivery name=" + 
						t.get().getDeliveryName() + " already exists in database and will not be saved");
				return true;
			}
		}
		return false;
	}
	
}
