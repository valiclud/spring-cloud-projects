package tacos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class TacoOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date placedAt;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="taco_order_id")
	private List<Taco> tacos = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="client_id")
  private Client client;
	
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
}
