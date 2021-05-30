package kodlamaio.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.dtos.JobPostingDto;

@Service
public class JobPostingManager implements JobPostingService{

	private JobPostingDao jobPostingDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public JobPostingManager(JobPostingDao jobPostingDao, ModelMapper modelMapper) {
		super();
		this.jobPostingDao = jobPostingDao;
		this.modelMapper=modelMapper;
	}
	
	private List<JobPostingDto> dtoGenerator(List<JobPosting> posting){
		List<JobPostingDto> jobPostingDtos = new ArrayList <JobPostingDto>();
		posting.forEach(item -> {
			JobPostingDto dto = this.modelMapper.map(item, JobPostingDto.class);
			dto.setCompanyName(item.getEmployer().getCompanyName());
			jobPostingDtos.add(dto);
		});
		return jobPostingDtos;
	}

	@Override
	public Result post(JobPosting jobPosting) {
		if(jobPostingDao.findByPostingIdEquals(jobPosting.getPostingId())!=null) {
			return new ErrorResult("This job posting is present");
		}
		else {
			this.jobPostingDao.save(jobPosting);
			return new SuccessResult("The job posting has been successfully added");
		}
	}

	@Override
	public DataResult<List<JobPostingDto>> findByActivityStatus() {
		return new SuccessDataResult<List<JobPostingDto>>
		(this.dtoGenerator(this.jobPostingDao.findByActivityStatus(true)));
	}
	
	@Override
	public DataResult<List<JobPostingDto>> findByActivityStatusAndApplicationDeadline() {
		return new SuccessDataResult<List<JobPostingDto>>
		(this.dtoGenerator(this.jobPostingDao.findByActivityStatusOrderByApplicationDeadline(true)));	
	}
	
	@Override
	public DataResult<List<JobPostingDto>> findByActivityStatusAndCompanyName(String companyName) {
		return new SuccessDataResult<List<JobPostingDto>>
		(this.dtoGenerator(this.jobPostingDao.findByActivityStatusAndEmployer_CompanyName(true,companyName)));		
	}

	@Override
	public Result delete(JobPosting jobPosting) {
		this.jobPostingDao.delete(jobPosting);
		return new SuccessResult("Deletion is successful");
		}
	}

