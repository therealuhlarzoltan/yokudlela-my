package hu.yokudlela.yokudlela.domain.validation.menuitem;

import hu.yokudlela.yokudlela.domain.validation.table.TableIdExists;
import hu.yokudlela.yokudlela.repository.MenuItemRepository;
import hu.yokudlela.yokudlela.repository.TableRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MenuItemExistsValidator implements ConstraintValidator<MenuItemIdExists, Long> {
    String message;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public void initialize(MenuItemIdExists constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(this.menuItemRepository.existsById(value)){
            return true;
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}
