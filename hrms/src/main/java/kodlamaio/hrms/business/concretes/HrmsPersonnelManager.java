package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.HrmsPersonnelService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.HrmsPersonnelDao;
import kodlamaio.hrms.entities.concretes.HrmsPersonnel;

@Service
public class HrmsPersonnelManager implements HrmsPersonnelService {

	private HrmsPersonnelDao hrmsPersonnelDao;

	@Autowired
	public HrmsPersonnelManager(HrmsPersonnelDao hrmsPersonnelDao) {
		this.hrmsPersonnelDao = hrmsPersonnelDao;
	}

	@Override
	public DataResult<List<HrmsPersonnel>> getAll() {
		return new SuccessDataResult<List<HrmsPersonnel>>(this.hrmsPersonnelDao.findAll(), "Listed");
	}

}
