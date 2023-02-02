package seed.row;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import seed.feature.Feature;
import seed.feature.FeatureService;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

	private VarietyService varietyService;
	private FeatureService featureService;

	@Autowired
	public  MyCommandLineRunner(VarietyService varietyService, FeatureService featureService) {
		this.varietyService = varietyService;
		this.featureService = featureService;
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		varietyService.save(new Variety(Long.valueOf(1), "Thymus vulgaris"));
		varietyService.save(new Variety(Long.valueOf(2), "Thymus x citriodorus"));
		varietyService.save(new Variety(Long.valueOf(3), "Thymus herba-barona"));
		varietyService.save(new Variety(Long.valueOf(4), "Thymus pseudolaginosus"));
		varietyService.save(new Variety(Long.valueOf(5), "Thymus serpyllum"));
		varietyService.save(new Variety(Long.valueOf(6), "Thymus futurus"));

		featureService.save(new Feature(Long.valueOf(1), "SEEDSTARTER_SPECIFIC_SUBSTRATE"));
		featureService.save(new Feature(Long.valueOf(2), "FERTILIZER"));
		featureService.save(new Feature(Long.valueOf(3), "PH_CORRECTOR"));
	}
}
