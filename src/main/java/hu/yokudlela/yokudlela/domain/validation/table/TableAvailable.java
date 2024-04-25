package hu.yokudlela.yokudlela.domain.validation.table;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TableAvailableContainer.class)
@Constraint(validatedBy = TableAvailableValidator.class)
public @interface TableAvailable {
    String message();
    String value();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
