package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="cv_experiences")
@AllArgsConstructor
@NoArgsConstructor
public class CvExperience {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="experience_id")
	private int experienceId;

	@Column(name="company")
	private String company;
	
	@Column(name="job_beginning_date")
	private LocalDate jobBeginnigDate;
	
	@Column(name="job_ending_date")
	private LocalDate jobEndingDate;

	@Column(name="position_name")
	private String positionName;
	
	@ManyToOne()
	@JoinColumn(name = "cv_id")
    @JsonIgnore()
	private CurriculumVitae curriculumVitae;
	

}