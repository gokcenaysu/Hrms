package kodlamaio.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="verification")
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name="user_id", referencedColumnName = "id")
//@AllArgsConstructor
@NoArgsConstructor
public class Verification{
	
	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="email_verification")
	private String emailVerification;
	
	@Column(name="is_verified")
	private boolean isVerified;
	
	@Column(name="verification_date")
	private LocalDateTime verificationDate = LocalDateTime.now();

	public Verification(int userId, String emailVerification, boolean isVerified, LocalDateTime verificationDate) {
		super();
		this.userId = userId;
		this.emailVerification = emailVerification;
		this.isVerified = isVerified;
		this.verificationDate = verificationDate;
	}

}
