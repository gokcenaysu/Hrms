package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.Section;

public interface SectionService {
	
	DataResult<List<Section>> getAll();
}
