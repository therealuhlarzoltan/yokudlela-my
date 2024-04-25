package hu.yokudlela.yokudlela.domain.validation.table;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface TableAvailableContainer {
    TableAvailable[] value();
}
