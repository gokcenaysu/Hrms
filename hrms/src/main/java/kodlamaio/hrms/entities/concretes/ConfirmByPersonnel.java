package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="confirm_by_personnel")
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name="employer_id", referencedColumnName = "user_id")
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmByPersonnel extends Employer{
	
	@Column(name="is_confirmed")
	private boolean confirmed;
}