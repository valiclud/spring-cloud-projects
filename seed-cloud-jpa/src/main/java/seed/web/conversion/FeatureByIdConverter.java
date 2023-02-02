
package seed.web.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import seed.feature.Feature;
import seed.feature.FeatureService;


@Component
public class FeatureByIdConverter implements 
Converter<String, Feature> {

	private final FeatureService featureService;
	
	@Autowired
	public FeatureByIdConverter(FeatureService featureService) {
		this.featureService = featureService;
	}
	
	@Override
	public Feature convert(String name) {
		Iterable<Feature> features = featureService.findAll();
		for (Feature f : features) {
			if (f.getName().equals(name))
				return f;
		}
		return null;
	}

}
