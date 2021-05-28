package kodlamaio.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="verification")
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name="user_id", referencedColumnName = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Verification{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="email_verification")
	private String emailVerification;
	
	@Column(name="is_verified")
	private boolean isVerified;
	
	@Column(name="verification_date")
	private Date verificationDate = new Date();
	

}
