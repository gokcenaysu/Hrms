package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlamaio.hrms.entities.concretes.ConfirmByPersonnel;

@Repository
public interface ConfirmByPersonnelDao extends JpaRepository<ConfirmByPersonnel, Integer>{
	
}
