package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CvPrewriting;

public interface CvPrewritingDao extends JpaRepository<CvPrewriting, Integer>{

	List<CvPrewriting> getAllByJobSeekerId(int id);
}
