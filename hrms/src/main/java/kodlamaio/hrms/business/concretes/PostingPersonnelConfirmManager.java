package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.PostingPersonnelConfirmService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.dataAccess.abstracts.PostingPersonnelConfirmDao;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.concretes.PostingPersonnelConfirm;

@Service
public class PostingPersonnelConfirmManager implements PostingPersonnelConfirmService {

	private PostingPersonnelConfirmDao postingPersonnelConfirmDao;
	private JobPostingDao jobPostingDao;

	@Autowired
	public PostingPersonnelConfirmManager(PostingPersonnelConfirmDao postingPersonnelConfirmDao,
			JobPostingDao jobPostingDao) {
		super();
		this.postingPersonnelConfirmDao = postingPersonnelConfirmDao;
		this.jobPostingDao = jobPostingDao;
	}

	PostingPersonnelConfirm posting = new PostingPersonnelConfirm();

	@Override
	public void createConfirm(JobPosting jobPosting) {
		posting.setJobPosting(jobPosting);
		posting.setPersonnelId(1);
		this.postingPersonnelConfirmDao.save(posting);
	}

	@Override
	public Result isConfirmedByPersonnel(int postingId) {
		if (!jobPostingDao.existsById(postingId)) {
			return new ErrorResult("Not present");
		}

		if (jobPostingDao.getByPostingIdEquals(postingId).isActivityStatus()) {
			return new ErrorResult("Already confirmed");
		}

		JobPosting jobPosting = new JobPosting();
		jobPosting = jobPostingDao.getByPostingIdEquals(postingId);
		jobPosting.setActivityStatus(true);
		jobPostingDao.save(jobPosting);
		posting = postingPersonnelConfirmDao.getByJobPosting(jobPosting.getPostingId());
		posting.setConfirmed(true);
		posting.setConfirmDate(LocalDateTime.now());
		postingPersonnelConfirmDao.save(posting);
		return new SuccessResult("Successful");
	}

}
