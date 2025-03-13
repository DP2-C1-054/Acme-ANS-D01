
package acme.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ReviewValidator.class)
public @interface ValidReview {

	String message() default "Invalid review. At least one of serviceReviewed, airlineReviewed, airportReviewed, or flightReviewed must be present.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
