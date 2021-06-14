package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "working_times")
@AllArgsConstructor
@NoArgsConstructor
public class WorkingTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "working_time_id")
	private int workingTimeId;

	@Column(name = "working_time")
	@NotNull
	@NotBlank
	private String workingTime;

	@OneToMany(mappedBy = "workingTime")
	@JsonIgnore
	private List<JobPosting> jobPosting;

}
