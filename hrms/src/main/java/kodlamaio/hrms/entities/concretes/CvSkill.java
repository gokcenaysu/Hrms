package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cv_skills")
@AllArgsConstructor
@NoArgsConstructor
public class CvSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "skill_id")
	private int programmingLanguageId;

	@NotBlank(message = "The field must be filled")
	@Column(name = "programming_language")
	private String programmingLanguage;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	@JsonIgnore()
	private JobSeeker jobSeeker;

}
