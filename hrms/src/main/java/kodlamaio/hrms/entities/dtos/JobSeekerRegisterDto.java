package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerRegisterDto {
		
	private String firstName;
	private String lastName;
	private String identityNumber;
	private String birthYear;
	private String email;
	private String password;
	private String rePassword;
}

