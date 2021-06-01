package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlamaio.hrms.entities.concretes.JobPosting;

@Repository
public interface JobPostingDao extends JpaRepository<JobPosting, Integer>{
	
	 JobPosting getByPostingIdEquals(int getPostingId);
	 List<JobPosting> getByActivityStatus(boolean getActivityStatus);
	 List<JobPosting> getByActivityStatusOrderByApplicationDeadline(boolean getActivityStatus);
	 List<JobPosting> getByActivityStatusAndEmployer_Id(boolean activityStatus, int getEmployerId);
}
