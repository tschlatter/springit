package ch.shades.demo.springit.domain.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//////// DAS IST EINE SELBER GEMACHTE ANNOTATION.... zu verwenden mit @PasswordsMatch

@Documented
@Constraint(validatedBy = PasswordsMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordsMatch {

	String message() default "Password & Password Confirmation do not match.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
