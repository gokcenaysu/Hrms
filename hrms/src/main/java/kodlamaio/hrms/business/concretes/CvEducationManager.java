package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvEducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvEducationDao;
import kodlamaio.hrms.entities.concretes.CvEducation;

@Service
public class CvEducationManager implements CvEducationService{
	
	 private final CvEducationDao cvEducationDao;

	    @Autowired
	    public CvEducationManager(CvEducationDao cvEducationDao) {
	        this.cvEducationDao = cvEducationDao;
	    }

	    @Override
	    public Result addAll(List<CvEducation> cvEducation) {
	        cvEducationDao.saveAll(cvEducation);
	        return new SuccessResult();
	    }

	    @Override
	    public Result add(CvEducation candidateEducation) {
	        this.cvEducationDao.save(candidateEducation);
	        return new SuccessResult();
	    }

	    @Override
	    public DataResult<List<CvEducation>> getAll() {
	        return new SuccessDataResult<>(this.cvEducationDao.findAll());
	    }

	    @Override
	    public DataResult<List<CvEducation>> getAllByJobSeekerIdOrderByGraduationYear(int jobSeekerId) {
	        return new SuccessDataResult<>(this.cvEducationDao.getAllByCvEduIdOrderByGraduationDate(jobSeekerId), "Listed");
	    }
}
