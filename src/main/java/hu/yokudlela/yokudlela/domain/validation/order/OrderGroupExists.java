package hu.yokudlela.yokudlela.domain.validation.order;

import hu.yokudlela.yokudlela.domain.validation.menuitem.MenuItemExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OrderGroupExistsValidator.class)
public @interface OrderGroupExists {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
