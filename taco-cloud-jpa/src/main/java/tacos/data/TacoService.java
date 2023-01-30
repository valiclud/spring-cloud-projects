package tacos.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.dto.IngredientDto;
import tacos.dto.TacoDto;
import tacos.entity.Ingredient;
import tacos.entity.Taco;

@Service
public class TacoService {

	@Autowired
	TacoRepository tacoRepository;
	
	public List<TacoDto> findAll(){
		Iterable<Taco> tacos = this.tacoRepository.findAll();
		List<TacoDto> listTacos = new ArrayList<>();
		if (tacos.iterator().hasNext()) {
			for (Taco taco : tacos) {
				TacoDto dto = Helper.toTacoDto(taco);
				listTacos.add(dto);
			}
		}
		return listTacos;
	}
	
	public Optional<TacoDto> save(TacoDto tacoDto) {
		if (Optional.of(tacoDto).isPresent()) {
			Taco taco = Helper.toTaco(tacoDto);
			
			Taco tacoSaved = this.tacoRepository.save(taco);
			if (Optional.of(tacoSaved).isPresent()) {
				TacoDto tacoDtoSaved = Helper.toTacoDto(tacoSaved);
				return Optional.of(tacoDtoSaved);
			}
		}
		return Optional.empty();
	}
	
	public Optional<TacoDto> findById(Long id) {
		Optional<Taco> opt = this.tacoRepository.findById(id);
		if (opt.isPresent()) {
			TacoDto tacoDto = Helper.toTacoDto(opt.get());
			return Optional.of(tacoDto);
		} else {
			return Optional.empty();
		}
	}
	
}
