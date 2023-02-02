package seed.row;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Variety {

	public Variety(Long varietyId, String name) {
		this.varietyId = varietyId;
		this.name = name;
	}
	
	public Variety() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long varietyId;

	private String name;
	
}
