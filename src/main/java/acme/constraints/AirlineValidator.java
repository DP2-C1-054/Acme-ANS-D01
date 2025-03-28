
package acme.constraints;

import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.airlines.Airline;
import acme.entities.airlines.AirlineRepository;

@Validator
public class AirlineValidator extends AbstractValidator<ValidAirline, Airline> {

	@Autowired
	private AirlineRepository repository;


	@Override
	protected void initialise(final ValidAirline annotation) {
		assert annotation != null;
	}
	@Override
	public boolean isValid(final Airline airline, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (airline == null)
			super.state(context, false, "*", "javax.validation.constraints.NotNull.message");

		else {
			String iataCode = airline.getIataCode();

			if (iataCode == null)
				super.state(context, false, "*", "javax.validation.constraints.NotNull.message");

			List<Airline> airlines = this.repository.findAllAirline();
			boolean isUnique = airlines.stream().filter(a -> a.getIataCode().equals(iataCode)).count() == 1;

			if (!isUnique)
				super.state(context, false, "*", "acme.validation.airline.iata-code.message");
		}

		result = !super.hasErrors(context);

		return result;
	}

}
