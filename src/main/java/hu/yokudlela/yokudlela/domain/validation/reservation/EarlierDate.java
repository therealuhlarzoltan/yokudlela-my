package hu.yokudlela.yokudlela.domain.validation.reservation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BeginBeforeEndValidator.class)
public @interface EarlierDate {
    String earlier();
    String later();

    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
