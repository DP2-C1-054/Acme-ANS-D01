
package acme.constraints;

import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.realms.flight_crew_members.FlightCrewMember;
import acme.realms.flight_crew_members.FlightCrewMemberRepository;

@Validator
public class FlightCrewMemberValidator extends AbstractValidator<ValidFlightCrewMember, FlightCrewMember> {

	@Autowired
	private FlightCrewMemberRepository repository;


	@Override
	protected void initialise(final ValidFlightCrewMember annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final FlightCrewMember flightCrewMember, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		if (flightCrewMember == null)
			super.state(context, false, "*", "javax.validation.constraints.NotNull.message");
		else {
			boolean codeContainsInitials = true;

			String code = flightCrewMember.getEmployeeCode();
			if (code == null)
				super.state(context, false, "*", "javax.validation.constraints.NotNull.message");

			String name = flightCrewMember.getUserAccount().getIdentity().getName();
			if (name == null)
				super.state(context, false, "*", "javax.validation.constraints.NotNull.message");

			String surname = flightCrewMember.getUserAccount().getIdentity().getSurname();
			if (surname == null)
				super.state(context, false, "*", "javax.validation.constraints.NotNull.message");

			char codeFirstChar = code.charAt(0);
			char codeSecondChar = code.charAt(1);
			char nameFirstChar = name.charAt(0);
			char surnameFirstChar = surname.charAt(0);

			if (!(codeFirstChar == nameFirstChar && codeSecondChar == surnameFirstChar))
				codeContainsInitials = false;

			super.state(context, codeContainsInitials, "*", "acme.validation.role.identifier.message");

			List<FlightCrewMember> flightCrewMembers = this.repository.findAllFlightCrewMembers();
			boolean isUnique = flightCrewMembers.stream().filter(a -> a.getEmployeeCode().equals(code)).count() == 1;

			if (!isUnique)
				super.state(context, false, "*", "acme.validation.flight-crew-member.code.message");

		}

		result = !super.hasErrors(context);

		return result;
	}

}
