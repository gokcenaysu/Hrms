package kodlamaio.hrms.core.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
//@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private boolean status;

	@Column(name = "created_on")
	private LocalDateTime createdOn = LocalDateTime.now();

	public User(int id, String email, String password, boolean status, LocalDateTime createdOn) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.status = status;
		this.createdOn = createdOn;
	}

}
