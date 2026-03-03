package tacos.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.data.TacoService;
import tacos.dto.TacoDto;

@Slf4j
@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = { "http://tacocloud:8080", "http://tacocloud.com" })
public class TacoController {

	private TacoService tacoService;

	public TacoController(TacoService tacoService) {
		this.tacoService = tacoService;
	}

	@GetMapping(params = "recent")
	public List<TacoDto> recentTacos() {
		return tacoService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TacoDto> tacoById(@PathVariable("id") Long id) {
		TacoDto optTaco = this.tacoService.findById(id);
		return new ResponseEntity<>(optTaco, HttpStatus.OK);
	}

}
