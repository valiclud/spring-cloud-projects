package tacos.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tacos.dto.ClientDto;
import tacos.entity.Client;

public interface ClientRepository 
          extends CrudRepository<Client, Long> {

  @Query("SELECT new tacos.dto.ClientDto(c.id, c.deliveryName, c.deliveryStreet, c.deliveryCity, "
	  	+ "c.deliveryState, c.deliveryZip, c.createdAt) FROM Client c WHERE c.deliveryName = :deliveryName")
  List<ClientDto> findByClientDtoDeliveryName(@Param("deliveryName") String deliveryName);
  
  @Query("SELECT new tacos.dto.ClientDto(c.id, c.deliveryName, c.deliveryStreet, c.deliveryCity, "
  		+ "c.deliveryState, c.deliveryZip, c.createdAt) FROM Client c")
  Iterable<ClientDto> findAllClientDto();
  
  @Query("SELECT new tacos.dto.ClientDto(c.id, c.deliveryName, c.deliveryStreet, c.deliveryCity, "
	  		+ "c.deliveryState, c.deliveryZip, c.createdAt) FROM Client c WHERE c.id = :id")
  Optional<ClientDto> findClientDtoById(@Param("id") Long id);
  
  List<Client> findByDeliveryName(String deliveryName);
    
}