package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlamaio.hrms.entities.concretes.EmployerPersonnelConfirm;

@Repository
public interface EmployerPersonnelConfirmDao extends JpaRepository<EmployerPersonnelConfirm, Integer> {

	EmployerPersonnelConfirm getByEmployerId(int id);

	boolean existsByEmployerId(int id);
}
