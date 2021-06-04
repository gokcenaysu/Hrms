package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CvPrewriting;

public interface CvPrewritingDao extends JpaRepository<CvPrewriting, Integer>{

}
