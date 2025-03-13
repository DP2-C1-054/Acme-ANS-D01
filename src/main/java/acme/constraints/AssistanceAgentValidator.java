
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.realms.assistance_agents.AssistanceAgent;

@Validator
public class AssistanceAgentValidator extends AbstractValidator<ValidAssistanceAgent, AssistanceAgent> {

	@Override
	protected void initialise(final ValidAssistanceAgent annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final AssistanceAgent assistanceAgent, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		if (assistanceAgent == null)
			super.state(context, false, "*", "javax.validation.constraints.NotNull.message");
		else {
			boolean codeContainsInitials = true;

			try {
				String code = assistanceAgent.getEmployeeCode();
				String name = assistanceAgent.getUserAccount().getIdentity().getName();
				String surname = assistanceAgent.getUserAccount().getIdentity().getSurname();

				char codeFirstChar = Character.toUpperCase(code.charAt(0));
				char codeSecondChar = Character.toUpperCase(code.charAt(1));
				char nameFirstChar = Character.toUpperCase(name.charAt(0));
				char surnameFirstChar = Character.toUpperCase(surname.charAt(0));

				if (!(codeFirstChar == nameFirstChar && codeSecondChar == surnameFirstChar))
					codeContainsInitials = false;

			} catch (Error e) {
				codeContainsInitials = false;
			}

			super.state(context, codeContainsInitials, "*", "acme.validation.role.identifier.message");
		}

		result = !super.hasErrors(context);

		return result;
	}

}
