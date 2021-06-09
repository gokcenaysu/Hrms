package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingDto {
	
	private String city;
	private String CompanyName;
	private String positionName;
	private String definition;
	private boolean activityStatus;
	private int openPositionNumber;
	private LocalDateTime postingDate;
	private LocalDate applicationDeadline;

}
