package hu.yokudlela.yokudlela.domain.validation.table;

import hu.yokudlela.yokudlela.domain.entity.Table;
import hu.yokudlela.yokudlela.repository.TableRepository;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.ConstraintValidator;

import java.util.Optional;

@Slf4j
public class TableAvailableValidator implements ConstraintValidator<TableAvailable, Long> {
    String message;

    String value;
    Class<?>[] groups;

    @Autowired
    private TableRepository tableRepository;

    @Override
    public void initialize(TableAvailable constraintAnnotation) {
        value = constraintAnnotation.value();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<Table> tbl = this.tableRepository.findById(value);
        if(this.value.isEmpty() ||
                (tbl.isPresent()  && tbl.get().isAvailable()==Boolean.parseBoolean(this.value))){
            return true;
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}
