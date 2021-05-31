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
	public Result update(int postingId, boolean activityStatus) {
        JobPosting jobPosting = getByPostingId(postingId).getData();
        if(jobPosting.isActivityStatus() && !activityStatus) {
            jobPosting.setActivityStatus(false);
            this.jobPostingDao.save(jobPosting);
            return new SuccessResult("Job posting deactivated");
            }
        
        else if(!jobPosting.isActivityStatus() && !activityStatus){
            return new ErrorResult("Job posting is already inactive");
        	}
        
        else if(!jobPosting.isActivityStatus() && activityStatus) {
            jobPosting.setActivityStatus(true);
            this.jobPostingDao.save(jobPosting);
            return new SuccessResult ("Job posting activated");
        }
        
        else if(jobPosting.isActivityStatus() && activityStatus){
       return new ErrorResult("Job posting is already active");
        }
        
        return null;
     }
	    
	@Override
	public Result delete(int postingId) {
		if(!getByPostingId(postingId).getData().isActivityStatus()) {
			this.jobPostingDao.deleteById(postingId);
			return new SuccessResult("Job posting removed");}
			else {
				return new ErrorResult("The job posting could not be removed because it is active");
			}
		}
	
	
	@Override
	public DataResult<JobPosting> getByPostingId(int postingId) {
		return new SuccessDataResult<JobPosting>(this.jobPostingDao.getOne(postingId));
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
	public DataResult<List<JobPostingDto>> findByActivityStatusAndEmployer(int employerId) {
		return new SuccessDataResult<List<JobPostingDto>>
		(this.dtoGenerator(this.jobPostingDao.findByActivityStatusAndEmployer_Id(true,employerId)));		
	}

	}

