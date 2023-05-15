package tacos.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tacos.entity.Client;

public interface ClientRepository 
          extends CrudRepository<Client, Long> {

  List<Client> findByDeliveryName(String deliveryName);
  
  Client findByUsername(String username);
  
}