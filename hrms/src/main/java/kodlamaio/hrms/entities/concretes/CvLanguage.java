package kodlamaio.hrms.entities.concretes;

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
@Table(name="cv_languages")
@AllArgsConstructor
@NoArgsConstructor
public class CvLanguage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cv_language_id")
	private int cvLanguageId;
	
	@Column(name="language_level")
	private int languageLevel;
	
	@OneToOne()
    @JoinColumn(name = "language_id")
    private Language language;
	
	@ManyToOne()
	@JoinColumn(name = "cv_id")
    @JsonIgnore()
    private CurriculumVitae curriculumVitae;
}
