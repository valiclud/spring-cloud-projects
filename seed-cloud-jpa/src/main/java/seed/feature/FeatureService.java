package seed.feature;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seed.seedstarter.SeedStarter;

@Service
public class FeatureService {

	@Autowired
	private FeatureRepository featureRepository;

	public Iterable<Feature> findAll() {
		return this.featureRepository.findAll();
	}

	public Optional<Feature> findById(Long id) {
		return this.featureRepository.findById(id);
	}
	
	public Feature save(Feature feature) {
		return this.featureRepository.save(feature);
	}

}
