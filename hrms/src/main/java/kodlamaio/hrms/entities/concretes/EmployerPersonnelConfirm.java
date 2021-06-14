package kodlamaio.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employer_personnel_confirm")
@AllArgsConstructor
@NoArgsConstructor
public class EmployerPersonnelConfirm {

	@Id
	@Column(name = "employer_id")
	@JsonIgnore()
	private int userId;

	@Column(name = "is_confirmed")
	private boolean confirmed;

	@Column(name = "personnel_id")
	private int personnelId;

	@Column(name = "confirm_date")
	private LocalDateTime confirmDate = LocalDateTime.now();
	
	@OneToOne(targetEntity = Employer.class)
	@JoinColumn(name = "employer_id")
	private Employer employer;
}
