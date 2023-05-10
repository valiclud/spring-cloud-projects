package tacos.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin(origins = {"http://tacocloud:8080", "http://tacocloud.com"})
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
  
  @PostMapping(consumes="application/json")
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
  
}
