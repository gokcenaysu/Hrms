package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cv_educations")
@AllArgsConstructor
@NoArgsConstructor
public class CvEducation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cv_edu_id")
	private int cvEduId;

	@NotBlank(message = "The field must be filled")
	@Column(name = "school_type")
	private String schoolType;

	@NotBlank(message = "The field must be filled")
	@PastOrPresent
	@Column(name = "edu_beginning_date")
	private LocalDate schoolBeginningDate;

	@PastOrPresent
	@Column(name = "graduation_date")
	private LocalDate graduationDate;

	@OneToOne()
	@JoinColumn(name = "university_id")
	private transient University university;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	@JsonIgnore()
	private JobSeeker jobSeeker;
}
