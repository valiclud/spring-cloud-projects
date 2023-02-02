package seed.row;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import seed.seedstarter.SeedStarter;

@Data
@Entity
public class SeedRow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer seedsPerCell;
	
	@OneToOne 
	@JoinColumn(name="fk_variety")
	private Variety variety;
}
