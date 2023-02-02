package seed.seedstarter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import seed.feature.Feature;
import seed.feature.FeatureService;
import seed.row.SeedRow;
import seed.row.Variety;
import seed.row.VarietyService;

@Slf4j
@Controller
//@SessionAttributes("seedStarter")
public class SeedStarterController {

	@Autowired
	private VarietyService varietyService;

	@Autowired
	private SeedStarterService seedStarterService;

	@Autowired
	private FeatureService featureService;

	@ModelAttribute("row")
	public SeedRow row() {
		return new SeedRow();
	}
	
	@ModelAttribute("allTypes")
	public List<SeedStarter.PackingType> populateTypes() {
		return Arrays.asList(SeedStarter.ALL);
	}

	@ModelAttribute("allFeatures")
	public List<String> populateFeatures() {
		Iterable<Feature> features = this.featureService.findAll();
		return Streamable.of(features).map(f -> f.getName()).toList();
	}

	@ModelAttribute("allVarieties")
	public List<Variety> populateVarieties() {
		Iterable<Variety> varieties = this.varietyService.findAll();
		return Streamable.of(varieties).toList();
	}

	@ModelAttribute("allSeedStarters")
	public List<SeedStarter> populateSeedStarters() {
		Iterable<SeedStarter> seeds = this.seedStarterService.findAll();
		return Streamable.of(seeds).toList();
	}
	
	/*
	 * Showing the front page
	 */
	@RequestMapping({ "/", "/seedstartermng" })
	public String showSeedstarters(final SeedStarter seedStarter) {
		seedStarter.setDatePlanted(Calendar.getInstance().getTime());
		return "seedstartermng";
	}

	/*
	 * Processing/adding new SeedStarter
	 */
	@RequestMapping(value = "/seedstartermng", params = { "save" })
	public String saveSeedstarter(final SeedStarter seedStarter, final ModelMap model) {
		this.seedStarterService.save(seedStarter);
		model.clear();
		return "redirect:/seedstartermng";
	}
	
	@RequestMapping(value="/seedstartermng", params={"addRow"})
    public String addRow(final SeedStarter seedStarter, final BindingResult bindingResult) {
        seedStarter.getSeedRows().add(new SeedRow());
        return "seedstartermng";
    }
    
    
    @RequestMapping(value="/seedstartermng", params={"removeRow"})
    public String removeRow(final SeedStarter seedStarter, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        seedStarter.getSeedRows().remove(rowId.intValue());
        return "seedstartermng";
    }
}
