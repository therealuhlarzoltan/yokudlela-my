package hu.yokudlela.yokudlela.domain.validation.order;

import hu.yokudlela.yokudlela.repository.OrderRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
public class OrderGroupExistsValidator implements ConstraintValidator<OrderGroupExists, String> {
    String message;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void initialize(OrderGroupExists constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(this.orderRepository.existsByOrderGroup(value)){
            return true;
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}
