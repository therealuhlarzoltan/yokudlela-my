package hu.yokudlela.yokudlela.domain.validation.menuitem;

import hu.yokudlela.yokudlela.domain.validation.table.TableIdExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MenuItemExistsValidator.class)
public @interface MenuItemIdExists {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
