
package acme.realms.technicians;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;

import acme.client.components.basis.AbstractRole;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;
import acme.constraints.ValidPhoneNumber;
import acme.constraints.ValidTechnician;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@ValidTechnician
public class Technician extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@ValidString(pattern = "^[A-Z]{2,3}\\d{6}$")
	@Column(unique = true)
	private String				licenseNumber;

	@Mandatory
	@ValidPhoneNumber
	@Automapped
	private String				phoneNumber;

	@Mandatory
	@ValidString(max = 50)
	@Automapped
	private String				specialisation;

	@Mandatory
	@Valid
	@Automapped
	private Boolean				annualHealthTestPassed;

	@Mandatory
	@ValidNumber(min = 0, max = 100)
	@Automapped
	private Integer				yearsOfExperience;

	@Optional
	@ValidString(max = 255)
	@Automapped
	private String				certification;
}
