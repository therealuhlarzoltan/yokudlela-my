package hu.yokudlela.yokudlela.domain.validation.order;

import hu.yokudlela.yokudlela.domain.enumeration.OrderState;
import hu.yokudlela.yokudlela.repository.OrderRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.UUID;

public class OrderStateExistsValidator implements ConstraintValidator<OrderStateExists, String>{
    String message;

    @Override
    public void initialize(OrderStateExists constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(Arrays.stream(OrderState.values()).map(OrderState::name).anyMatch(s -> s.equals(value))){
            return true;
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}
