package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LinkService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LinkDao;
import kodlamaio.hrms.entities.concretes.Link;

@Service
public class LinkManager implements LinkService {

	private final LinkDao linkDao;

	@Autowired
	    public LinkManager(LinkDao linkDao) {
	        this.linkDao = linkDao;
	    }

	@Override
	public Result add(Link link) {
		this.linkDao.save(link);
		return new SuccessResult();
	}

	@Override
	public Result addAll(List<Link> link) {
		linkDao.saveAll(link);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<Link>> getAll() {
		return new SuccessDataResult<>(this.linkDao.findAll());
	}

	@Override
	public DataResult<List<Link>> getAllByJobSeekerId(int jobSeekerId) {
		return new SuccessDataResult<>(this.linkDao.getAllByUserId(jobSeekerId));
	}
}
