package kodlamaio.hrms.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kodlamaio.hrms.entities.concretes.CvEducation;
import kodlamaio.hrms.entities.concretes.CvExperience;
import kodlamaio.hrms.entities.concretes.CvLanguage;
import kodlamaio.hrms.entities.concretes.CvPrewriting;
import kodlamaio.hrms.entities.concretes.CvSkill;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Link;
import kodlamaio.hrms.entities.concretes.Photograph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCvDto {

	@JsonIgnore()
	private JobSeeker jobSeeker;
	private List<CvEducation> cvEducation;
	private List<CvExperience> cvExperience;
	private List<CvLanguage> cvLanguage;
	private List<CvPrewriting> cvPrewriting;
	private List<CvSkill> cvSkill;
	private List<Photograph> photograph;
	private List<Link> link;

}
