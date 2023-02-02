package seed.row;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeedRowService {

	@Autowired
	private SeedRowRepository rowRepository;

	public Iterable<SeedRow> findAll() {
		return this.rowRepository.findAll();
	}

	public Optional<SeedRow> findById(Long id) {
		return this.rowRepository.findById(id);
	}

}
