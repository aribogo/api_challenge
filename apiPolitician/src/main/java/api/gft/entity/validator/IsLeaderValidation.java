package api.gft.entity.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { PoliticianValidator.class })
public @interface IsLeaderValidation {

	String message() default "This person is not allowed to be a party leader.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	
}
