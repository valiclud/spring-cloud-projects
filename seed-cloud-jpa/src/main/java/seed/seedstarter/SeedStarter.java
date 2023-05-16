package seed.seedstarter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import seed.feature.Feature;
import seed.row.SeedRow;

@Data
@Entity
public class SeedStarter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date datePlanted;

	private Boolean covered;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Feature_Ref")
	private List<Feature> features = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_seed_starter")
	private List<SeedRow> seedRows = new ArrayList<>();
	
	public void addFeature(Feature feature) {
		this.features.add(feature);
	}
	
	public PackingType packingType = PackingType.PLASTIC;
	
	enum PackingType { PLASTIC, WOOD }
	
	public static final PackingType[] ALL = PackingType.values();
	
	   public PackingType getType() {
	        return this.packingType;
	    }


	    public void setType(final PackingType packingType) {
	        this.packingType = packingType;
	    }

	    
}
