package tacos.dto.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.dto.ClientDto;
import tacos.entity.Client;

@Service
public class ClientDTOMapper implements Function<Client, ClientDto> {

	@Override
	public ClientDto apply(Client client) {
		return new ClientDto(
				client.getId(),
				client.getCreatedAt(),
				client.getDeliveryName(),
				client.getDeliveryStreet(),
				client.getDeliveryCity(),
				client.getDeliveryState(),
				client.getDeliveryZip());
	}

}
