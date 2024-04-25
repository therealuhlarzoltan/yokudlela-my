package hu.yokudlela.yokudlela.domain.validation.table;

import hu.yokudlela.yokudlela.repository.TableRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TableIdExistsValidator  implements ConstraintValidator<TableIdExists, Long> {
    String message;

    @Autowired
    private TableRepository tableRepository;

    @Override
    public void initialize(TableIdExists constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(this.tableRepository.existsById(value)){
            return true;
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}