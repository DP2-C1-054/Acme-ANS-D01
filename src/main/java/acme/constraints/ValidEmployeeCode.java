
package acme.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ReportAsSingleViolation

@Pattern(regexp = "^[A-Z]{2,3}\\d{6}$")
public @interface ValidEmployeeCode {

	String message() default "{acme.validation.assistance-agent.employee-code.message}";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
