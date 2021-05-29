package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlamaio.hrms.entities.concretes.JobPosting;

@Repository
public interface JobPostingDao extends JpaRepository<JobPosting, Integer>{
	
	 JobPosting findByPostingIdEquals(int positionId);
	
	 List<JobPosting> findByActivityStatus(boolean activityStatus);
	 List<JobPosting> findByActivityStatusOrderByApplicationDeadline(boolean activityStatus);
	 List<JobPosting> findByActivityStatusAndEmployer_CompanyName(boolean activityStatus, String companyName);
}
