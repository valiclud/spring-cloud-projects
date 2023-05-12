package tacos.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
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
public class TacoClientController {

  private ClientService clientService;

  public TacoClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping(params = "all")
  public List<ClientDto> allClients() {
    List<ClientDto> clients = clientService.findAll();

    return clients;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientDto> clientById(@PathVariable("id") Long id) {
    Optional<ClientDto> optClient = this.clientService.findById(id);
    if (optClient.isPresent()) {
      return new ResponseEntity<>(optClient.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ClientDto> postClient(@RequestBody ClientDto client) {
    List<ClientDto> clients = this.clientService.findByDeliveryName(client.getDeliveryName());
    if (!clients.isEmpty())
      return new ResponseEntity<>(clients.get(0), HttpStatus.CONFLICT);

    Optional<ClientDto> optClient = this.clientService.save(client);
    if (optClient.isPresent()) {
      return new ResponseEntity<>(optClient.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
  }

  @PatchMapping(path = "/{clientId}", consumes = "application/json")
  public ResponseEntity<ClientDto> patchClient(@PathVariable("clientId") Long clientId, @RequestBody ClientDto patch) {

    Optional<ClientDto> clientDto = this.clientService.findById(clientId);
    if (clientDto.isPresent()) {
      if (patch.getDeliveryName() != null) {
        clientDto.get().setDeliveryName(patch.getDeliveryName());
      }
      if (patch.getDeliveryStreet() != null) {
        clientDto.get().setDeliveryStreet(patch.getDeliveryStreet());
      }
      if (patch.getDeliveryCity() != null) {
        clientDto.get().setDeliveryCity(patch.getDeliveryCity());
      }
      if (patch.getDeliveryState() != null) {
        clientDto.get().setDeliveryState(patch.getDeliveryState());
      }
      if (patch.getDeliveryZip() != null) {
        clientDto.get().setDeliveryZip(patch.getDeliveryZip());
      }
      if (patch.getCcNumber() != null) {
        clientDto.get().setCcNumber(patch.getCcNumber());
      }
      if (patch.getCcExpiration() != null) {
        clientDto.get().setCcExpiration(patch.getCcExpiration());
      }
      if (patch.getCcCVV() != null) {
        clientDto.get().setCcCVV(patch.getCcCVV());
      }
      Optional<ClientDto> savedDto = this.clientService.save(clientDto.get());
      return new ResponseEntity<>(savedDto.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
  }
  
  @DeleteMapping("/{clientId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<ClientDto> deleteOrder(@PathVariable("clientId") Long clientId) {
    boolean isDeleted = this.clientService.deleteById(clientId);
    if (isDeleted) {
      Optional<ClientDto> optClient = this.clientService.findById(clientId);
      if (optClient.isPresent()) {
        return new ResponseEntity<>(optClient.get(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
  }

}
