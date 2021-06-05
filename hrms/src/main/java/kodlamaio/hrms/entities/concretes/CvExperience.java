package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cv_experiences")
@AllArgsConstructor
@NoArgsConstructor
public class CvExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "experience_id")
	private int cvExperienceId;

	@Column(name = "company")
	@NotBlank(message = "The field must be filled")
	private String company;

	@NotNull(message = "The field must be filled")
	@PastOrPresent
	@Column(name = "job_beginning_date")
	private LocalDate jobBeginnigDate;

	@PastOrPresent
	@Column(name = "job_ending_date")
	private LocalDate jobEndingDate;

	@NotBlank(message = "The field must be filled")
	@Column(name = "position_name")
	private String positionName;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	@JsonIgnore()
	private JobSeeker jobSeeker;

}
