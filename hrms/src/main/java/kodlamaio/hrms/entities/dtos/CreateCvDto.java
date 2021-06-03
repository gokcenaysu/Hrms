package kodlamaio.hrms.entities.dtos;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.CvEducation;
import kodlamaio.hrms.entities.concretes.CvExperience;
import kodlamaio.hrms.entities.concretes.CvLanguage;
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
	private CurriculumVitae curriculumVitae;
	private List<@Valid CvEducation> cvEducations;
	private List<@Valid CvExperience> cvExperiences;
	private List<@Valid CvLanguage> cvLanguages;
	private List<@Valid CvSkill> cvSkills;
	private List<@Valid Photograph> photograph;
	private List<@Valid Link> link;

}
