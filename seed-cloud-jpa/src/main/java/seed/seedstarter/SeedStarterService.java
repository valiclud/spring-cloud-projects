package seed.seedstarter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeedStarterService {

	@Autowired
	SeedStarterRepository seedStarterRepository;
	
	public Iterable<SeedStarter> findAll(){
		return this.seedStarterRepository.findAll();
	}
	
	public SeedStarter save(SeedStarter seedStarter) {
		return this.seedStarterRepository.save(seedStarter);
	}
	
	public Optional<SeedStarter> find(Long id) {
		return this.seedStarterRepository.findById(id);
	}
}
