package tacos.dto.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.dto.ClientDto;
import tacos.entity.Client;

@Service
public class DTOClientMapper implements Function<ClientDto, Client> {

	@Override
	public Client apply(ClientDto client) {
		return new Client(
				client.getId(),
				client.getDeliveryName(),
				client.getDeliveryStreet(),
				client.getDeliveryCity(),
				client.getDeliveryState(),
				client.getDeliveryZip(),
				client.getCreatedAt());
	}

}
