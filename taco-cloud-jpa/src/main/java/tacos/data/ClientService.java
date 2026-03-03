package tacos.data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.ClientDto;
import tacos.dto.mapper.ClientDTOMapper;
import tacos.dto.mapper.DTOClientMapper;
import tacos.entity.Client;

@Slf4j
@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ClientDTOMapper clientDTOMapper;

	@Autowired
	DTOClientMapper dtoClientMapper;

	public List<ClientDto> findAll() {
		Iterable<Client> clients = this.clientRepository.findAll();
		return StreamSupport.stream(clients.spliterator(), false).map(clientDTOMapper).toList();
	}

	public List<ClientDto> findByDeliveryName(String deliveryName) {
		List<Client> clients = this.clientRepository.findByDeliveryName(deliveryName);
		return StreamSupport.stream(clients.spliterator(), false).map(clientDTOMapper).collect(Collectors.toList());
	}

	public void save(ClientDto clientDto) {
		if (!this.clientRepository.existsById(clientDto.getId())) {
			this.clientRepository.save(dtoClientMapper.apply(clientDto));
		}
		throw new ResourceAccessException("Client with id [%s] already exists ".formatted(clientDto.getId()));
	}

	public ClientDto findById(Long id) {
		return this.clientRepository.findById(id).map(clientDTOMapper)
				.orElseThrow(() -> new ResourceNotFoundException("Client with id [%s] not found ".formatted(id)));
	}

	public void deleteById(Long id) {
		if (!this.clientRepository.existsById(id)) {
			throw new ResourceNotFoundException("Client with id [%s] not found ".formatted(id));
		}
		this.clientRepository.deleteById(id);
	}

}
