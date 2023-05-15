package tacos.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.ClientDto;
import tacos.entity.Client;

@Slf4j
@Service
public class ClientService {

  @Autowired
  ClientRepository clientRepository;

  public List<ClientDto> findAll() {
    Iterable<Client> clients = this.clientRepository.findAll();
    List<ClientDto> clientsDto = new ArrayList<>();
    if (clients.iterator().hasNext()) {
      for (Client client : clients) {
        ClientDto dto = Helper.toClientDto(client);
        clientsDto.add(dto);
      }
    }
    return clientsDto;
  }

  public List<ClientDto> findByDeliveryName(String deliveryName) {
    List<Client> clients = this.clientRepository.findByDeliveryName(deliveryName);
    List<ClientDto> clientsDto = new ArrayList<>();
    if (!clients.isEmpty()) {
      for (Client client : clients) {
        ClientDto dto = Helper.toClientDto(client);
        clientsDto.add(dto);
      }
    }
    return clientsDto;
  }

  public Optional<ClientDto> save(ClientDto clientDto) {
    if (Optional.of(clientDto).isPresent()) {
      Client client = Helper.toClient(clientDto);
      Client clientSaved = this.clientRepository.save(client);
      if (Optional.of(clientSaved).isPresent()) {
        ClientDto clientDtoSaved = Helper.toClientDto(clientSaved);
        return Optional.of(clientDtoSaved);
      }
    }
    return Optional.empty();
  }

  public Optional<ClientDto> findById(Long id) {
    if (!Optional.of(id).isEmpty()) {
      Optional<Client> client = this.clientRepository.findById(id);
      if (!client.isEmpty())
        return Optional.of(Helper.toClientDto(client.get()));
    }
    return Optional.empty();
  }

  public boolean deleteById(Long id) {
    if (id != null) {
      Optional<Client> client = this.clientRepository.findById(id);
      if (!client.isEmpty()) {
        this.clientRepository.deleteById(id);
        return true;
      }
    }
    log.info("Client ID "+id+"does not exist in database so could not be deleted");
    return false;
  }

}
