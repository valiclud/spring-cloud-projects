package seed.feature;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;

	private final String name;
	
	public String getName() {
		return name;
	}
}
