package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="curriculum_vitae")
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitae {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cv_id")
	private int cvId;
	
	@Column(name="cover_letter")
	private String coverLetter;
	
	@Column(name="created_on")
	private LocalDateTime createdOn = LocalDateTime.now();
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	
	@Column(name="activity")
	private boolean activity;
	
	
	@OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore()
	private List<CvEducation> cvEducation;
	
	@OneToMany(mappedBy = "curriculumVitae")
	@JsonIgnore()
	private List<CvLanguage> cvLanguage;
	
	@OneToMany(mappedBy="curriculumVitae")
	@JsonIgnore()
	private List<CvSkill> cvSkill;
	
	@OneToMany(mappedBy="curriculumVitae")
	@JsonIgnore()
	private List<CvExperience> cvExperience;
	
	@OneToOne()
    @JoinColumn(name = "jobseeker_id")
	@JsonIgnore()
    private JobSeeker jobSeeker;
	
	
}
