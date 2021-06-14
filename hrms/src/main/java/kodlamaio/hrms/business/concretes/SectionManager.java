package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SectionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.SectionDao;
import kodlamaio.hrms.entities.concretes.Section;

@Service
public class SectionManager implements SectionService{
	private SectionDao sectionDao;
	
	@Autowired
	public SectionManager(SectionDao sectionDao) {
		super();
		this.sectionDao = sectionDao;
	}
	
	@Override
	public DataResult<List<Section>> getAll() {
		return new SuccessDataResult<List<Section>>(this.sectionDao.findAll());
	}

}
