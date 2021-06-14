package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlamaio.hrms.entities.concretes.JobPosting;

@Repository
public interface JobPostingDao extends JpaRepository<JobPosting, Integer>{
	
	 JobPosting getByPostingIdEquals(int postingId);
	 List<JobPosting> getByActivityStatus(boolean activityStatus);
	 List<JobPosting> getByActivityStatusOrderByApplicationDeadline(boolean activityStatus);
	 List<JobPosting> getByActivityStatusAndEmployerId(boolean activityStatus, int employerId);
}
