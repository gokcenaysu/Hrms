package kodlamaio.hrms.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingDto {

	private String companyName;
	private String positionName;
	private int openPositionNumber;
	private LocalDateTime postingDate;
	private String applicationDeadline;

}
