package seed.row;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VarietyService {

	@Autowired
	private VarietyRepository varietyRepository;

	public Iterable<Variety> findAll() {
		return this.varietyRepository.findAll();
	}

	public Optional<Variety> findById(Long id) {
		return this.varietyRepository.findById(id);
	}

	public Variety save(Variety variety) {
		return this.varietyRepository.save(variety);
	}
}
