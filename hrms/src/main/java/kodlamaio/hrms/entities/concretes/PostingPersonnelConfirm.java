package kodlamaio.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "posting_personnel_confirm")
@AllArgsConstructor
@NoArgsConstructor
public class PostingPersonnelConfirm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "confirm_id")
	private int confirm_id;
	
	@Column(name="is_confirmed")
	private boolean isConfirmed;
	
	@Column(name="personnel_id")
	private int personnelId;
	
	@Column(name="confirm_date")
	private LocalDateTime confirmDate = LocalDateTime.now();
	
	@OneToOne()
	@JoinColumn(name="job_posting_id")
	private JobPosting jobPosting;
}
