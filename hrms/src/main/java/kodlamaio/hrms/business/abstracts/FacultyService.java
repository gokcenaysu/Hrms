package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.Faculty;

public interface FacultyService {
	
	DataResult <List<Faculty>> getAll();

}
