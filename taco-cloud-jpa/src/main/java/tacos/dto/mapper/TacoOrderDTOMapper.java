package tacos.dto.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.dto.ClientDto;
import tacos.dto.TacoOrderDto;
import tacos.entity.TacoOrder;

@Service
public class TacoOrderDTOMapper implements Function<TacoOrder, TacoOrderDto> {

	@Autowired
	TacoDTOMapper tacoDTOMapper;
	@Autowired
	ClientDTOMapper clientDTOMapper;
	
	@Override
	public TacoOrderDto apply(TacoOrder order) {
		return new TacoOrderDto(
				order.getId(),
				order.getPlacedAt(),
				order.getCcNumber(),
				order.getCcExpiration(),
				order.getCcCvv(),
				clientDTOMapper.apply(order.getClient()),
				order.getTacos()
				.stream()
				.map(tacoDTOMapper)
				.toList());
	}
}
