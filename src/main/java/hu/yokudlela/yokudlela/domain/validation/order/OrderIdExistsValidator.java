package hu.yokudlela.yokudlela.domain.validation.order;

import hu.yokudlela.yokudlela.domain.validation.menuitem.MenuItemIdExists;
import hu.yokudlela.yokudlela.repository.MenuItemRepository;
import hu.yokudlela.yokudlela.repository.OrderRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class OrderIdExistsValidator implements ConstraintValidator<OrderIdExists, String> {
    String message;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void initialize(OrderIdExists constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(this.orderRepository.existsById(UUID.fromString(value))){
            return true;
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}
