
package acme.entities.passenger;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidEmail;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidString;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Passenger extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@ValidString
	@Automapped
	private String				name;

	@Mandatory
	@ValidEmail
	@Automapped
	private String				mail;

	@Mandatory
	@ValidString(pattern = "^[A-Z0-9]{6,9}$")
	@Column(unique = true)
	private String				passport;

	@Mandatory
	@ValidMoment(past = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				birthDate;

	@Optional
	@ValidString(max = 50)
	@Automapped
	private String				specialNeeds;

}
