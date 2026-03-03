package tacos.web.api;

import java.util.List;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.data.ClientService;
import tacos.data.TacoService;
import tacos.dto.ClientDto;
import tacos.dto.TacoDto;
import tacos.dto.TacoOrderDto;

@Slf4j
@RestController
@RequestMapping(path = "/api/clients", produces = "application/json")
@CrossOrigin(origins = { "http://tacocloud:8080", "http://tacocloud.com" })
public class ClientController {

	private ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping(params = "all")
	public List<ClientDto> allClients() {
		List<ClientDto> clients = clientService.findAll();

		return clients;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> clientById(@PathVariable("id") Long id) {
			return new ResponseEntity<>(this.clientService.findById(id), HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> postClient(@RequestBody ClientDto client) {
			this.clientService.save(client);
			return new ResponseEntity<>(client, HttpStatus.CREATED);
	}

	@PatchMapping(path = "/{clientId}", consumes = "application/json")
	public ResponseEntity<ClientDto> patchClient(@PathVariable("clientId") Long clientId,
			@RequestBody ClientDto patch) {
		try {
			ClientDto clientDto = this.clientService.findById(clientId);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		/*
		 * if (patch.deliveryName() != null) { clientDto = new
		 * ClientDto(client.getId(),client.getCreatedAt(),client.getDeliveryName(),
		 * client.getDeliveryStreet(), client.getDeliveryCity(),
		 * client.getDeliveryState(), client.getDeliveryZip() );
		 * 
		 * 
		 * clientDto.get().setDeliveryName(patch.deliveryName()); } if
		 * (patch.deliveryStreet() != null) {
		 * clientDto.get().setDeliveryStreet(patch.getDeliveryStreet()); } if
		 * (patch.deliveryCity() != null) {
		 * clientDto.get().setDeliveryCity(patch.getDeliveryCity()); } if
		 * (patch.deliveryState() != null) {
		 * clientDto.get().setDeliveryState(patch.getDeliveryState()); } if
		 * (patch.deliveryZip() != null) {
		 * clientDto.get().setDeliveryZip(patch.getDeliveryZip()); }
		 */
		return null;
	}

	@DeleteMapping("/{clientId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Long> deleteOrder(@PathVariable("clientId") Long clientId) {
		try {
			this.clientService.deleteById(clientId);
			return new ResponseEntity<>(clientId, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(clientId, HttpStatus.NOT_FOUND);
		}
	}

}
