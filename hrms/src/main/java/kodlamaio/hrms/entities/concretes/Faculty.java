package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "faculties")
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faculty_id")
	private int facultyId;

	@Column(name = "faculty_name")
	private String facultyName;

	@Column(name = "status")
	private int status;

	@OneToMany(mappedBy = "faculty")
	private List<Section> section;

	@ManyToOne()
	@JoinColumn(name = "university_id")
	private University university;
}
