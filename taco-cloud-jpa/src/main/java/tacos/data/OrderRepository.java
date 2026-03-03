package tacos.data;
 
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tacos.dto.ClientDto;
import tacos.dto.TacoOrderDto;
import tacos.entity.TacoOrder;
 
public interface OrderRepository 
         extends CrudRepository<TacoOrder, Long> {

	  @Query("SELECT new tacos.dto.ClientDto(c.id, c.deliveryName, c.deliveryStreet, c.deliveryCity, "
		  	+ "c.deliveryState, c.deliveryZip, c.createdAt) FROM Client c WHERE c.deliveryName = :deliveryName")
	  List<ClientDto> findByDeliveryName(@Param("deliveryName") String deliveryName);
	  
	  @Query("SELECT new tacos.dto.TacoOrderDto(to.ccNumber, to.ccExpiration, to.ccCvv, "
	  		+ "c.deliveryName, c.deliveryStreet, c.deliveryCity, "
	  		+ "c.deliveryState, c.deliveryZip, to.placedAt, c.id) "
	  		+ "FROM TacoOrder to "
	  		+ "JOIN Client c ON to.client.id = c.id")
	  		//+ "JOIN Taco t ON t.taco_order_id = to.id")
	  Iterable<TacoOrderDto> findAllTacoOrderDto();
	  
	  @Query("SELECT new tacos.dto.TacoOrderDto(to.ccNumber, to.ccExpiration, to.ccCvv, "
		  		+ "c.deliveryName, c.deliveryStreet, c.deliveryCity, "
		  		+ "c.deliveryState, c.deliveryZip, to.placedAt, c.id) "
		  		+ "FROM TacoOrder to "
		  		+ "JOIN Client c ON to.client.id = c.id "
		  		+ "WHERE c.id = :id")
	  Optional<TacoOrderDto> findTacoOrderDtoById(@Param("id") Long id);
	   
}