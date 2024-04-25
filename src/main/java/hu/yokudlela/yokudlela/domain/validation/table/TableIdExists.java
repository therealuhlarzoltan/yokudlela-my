package hu.yokudlela.yokudlela.domain.validation.table;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TableIdExistsValidator.class)
public @interface TableIdExists {

    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}