package tacos.jmx;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import tacos.entity.Taco;
import tacos.data.TacoRepository;

@Service
@ManagedResource
public class TacoCounter extends AbstractRepositoryEventListener<Taco> {

	private TacoRepository tacoRepo;

	public TacoCounter(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}

	@Override
	protected void onAfterCreate(Taco entity) {
	}

	@ManagedAttribute
	public long getTacoCount() {
		return tacoRepo.count();
	}

}
