package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "job_postings")
@AllArgsConstructor
@NoArgsConstructor
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "posting_id")
	private int postingId;

	@Column(name = "job_definition")
	@NotNull
	private String jobDefinition;

	@Column(name = "min_salary")
	private double minSalary;

	@Column(name = "max_salary")
	private double maxSalary;

	@Column(name = "open_position_number")
	@NotNull
	private int openPositionNumber;

	@FutureOrPresent
	@Column(name = "application_deadline")
	private LocalDate applicationDeadline;

	@Column(name = "posting_date")
	private LocalDateTime postingDate = LocalDateTime.now();

	@Column(name = "activity_status")
	private boolean activityStatus;
	
	@Column(name="is_remote")
	private boolean isRemote;

	@ManyToOne()
	@JoinColumn(name = "employer_id")
	private Employer employer;

	@ManyToOne()
	@JoinColumn(name = "position_id")
	private JobPosition jobPosition;

	@ManyToOne()
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne()
	@JoinColumn(name="working_time_id")
	private WorkingTime workingTime;
	
	@OneToOne(mappedBy="jobPosting")
	private PostingPersonnelConfirm postingPersonnelConfirm;
}
