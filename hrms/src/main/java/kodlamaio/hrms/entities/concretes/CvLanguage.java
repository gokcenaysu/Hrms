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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cv_languages")
@AllArgsConstructor
@NoArgsConstructor
public class CvLanguage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cv_language_id")
	private int cvLanguageId;

	@Column(name = "language_level")
	@Min(1)
	@Max(5)
	private int languageLevel;

	@OneToOne()
	@NotBlank(message = "The field must be filled")
	@JoinColumn(name = "language_id")
	private Language language;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	@JsonIgnore()
	private JobSeeker jobSeeker;
}
