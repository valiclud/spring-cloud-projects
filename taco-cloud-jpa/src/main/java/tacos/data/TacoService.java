package tacos.data;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import tacos.dto.TacoDto;
import tacos.dto.mapper.DTOTacoMapper;
import tacos.dto.mapper.TacoDTOMapper;
import tacos.entity.Taco;

@Service
public class TacoService {

	@Autowired
	TacoRepository tacoRepository;

	@Autowired
	TacoDTOMapper tacoDTOMapper;

	@Autowired
	DTOTacoMapper dtoTacoMapper;

	public List<TacoDto> findAll() {
		Iterable<Taco> tacos = this.tacoRepository.findAll();
		return StreamSupport.stream(tacos.spliterator(), false).map(tacoDTOMapper).toList();
	}

	public void save(TacoDto tacoDto) {
		if (!this.tacoRepository.existsById(tacoDto.getId())) {
			this.tacoRepository.save(dtoTacoMapper.apply(tacoDto));
		}
		throw new ResourceAccessException("Taco with id [%s] already exists ".formatted(tacoDto.getId()));

	}

	public TacoDto findById(Long id) {
		return this.tacoRepository.findById(id).map(tacoDTOMapper)
				.orElseThrow(() -> new ResourceNotFoundException("Taco with id [%s] not found ".formatted(id)));
	}

}
