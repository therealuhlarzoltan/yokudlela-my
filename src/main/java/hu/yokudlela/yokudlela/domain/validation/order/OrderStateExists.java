package hu.yokudlela.yokudlela.domain.validation.order;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OrderStateExistsValidator.class)
public @interface OrderStateExists {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
