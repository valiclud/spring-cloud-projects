package tacos.data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import tacos.dto.ClientDto;
import tacos.dto.TacoOrderDto;
import tacos.dto.mapper.ClientDTOMapper;
import tacos.dto.mapper.DTOClientMapper;
import tacos.dto.mapper.DTOTacoOrderMapper;
import tacos.dto.mapper.TacoOrderDTOMapper;
import tacos.entity.TacoOrder;

@Slf4j
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	TacoOrderDTOMapper tacoOrderDTOMapper;

	@Autowired
	DTOTacoOrderMapper dtoTacoOrderMapper;

	public List<TacoOrderDto> findAll() {
		Iterable<TacoOrder> tacos = this.orderRepository.findAll();
		return StreamSupport.stream(tacos.spliterator(), false).map(tacoOrderDTOMapper).toList();
	}

	public void save(TacoOrderDto tacoOrderDto) {
		if (!this.orderRepository.existsById(tacoOrderDto.getId())) {
			this.orderRepository.save(dtoTacoOrderMapper.apply(tacoOrderDto));
		}
		throw new ResourceAccessException("Taco Order with id [%s] already exists ".formatted(tacoOrderDto.getId()));
	}

	public TacoOrderDto findById(Long id) {
		return this.orderRepository.findById(id).map(tacoOrderDTOMapper)
				.orElseThrow(() -> new ResourceNotFoundException("Taco Order with id [%s] not found ".formatted(id)));
	}

	public void deleteById(long id) {
		if (!this.orderRepository.existsById(id)) {
			throw new ResourceNotFoundException("Taco Order with id [%s] not found ".formatted(id));
		}
		this.orderRepository.deleteById(id);
	}

}
