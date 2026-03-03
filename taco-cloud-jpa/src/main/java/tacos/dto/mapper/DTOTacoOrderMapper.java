package tacos.dto.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.dto.TacoOrderDto;
import tacos.entity.TacoOrder;

@Service
public class DTOTacoOrderMapper implements Function<TacoOrderDto, TacoOrder> {

	@Autowired
	DTOClientMapper dTOClientMapper;
	@Autowired
	DTOTacoMapper dTOTacoMapper;
	
	@Override
	public TacoOrder apply(TacoOrderDto order) {
		return new TacoOrder(
				order.getId(),
				order.getCcNumber(),
				order.getCcExpiration(),
				order.getCcCvv(),
				order.getPlacedAt(),
				order.getTacos()
				.stream()
				.map(dTOTacoMapper)
				.toList(), 
				dTOClientMapper.apply(order.getClientDto()));
	}
}