package kodlamaio.hrms.entities.concretes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="cv_skills")
@AllArgsConstructor
@NoArgsConstructor
public class CvSkill {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="skill_id")
	private int programmingLanguageId;
	
	@Column(name="programming_language")
	private String programmingLanguage;
	
	@ManyToOne()
	@JoinColumn(name = "cv_id")
    @JsonIgnore()
	private CurriculumVitae curriculumVitae;
	
}
