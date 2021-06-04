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
@Table(name = "cv_prewritings")
@AllArgsConstructor
@NoArgsConstructor
public class CvPrewriting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cv_prewriting_id")
	private int cvPrewritingId;

	@NotBlank(message = "The field must be filled")
	@Column(name = "prewriting")
	private String prewriting;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	@JsonIgnore()
	private JobSeeker jobSeeker;

}
