package hu.yokudlela.yokudlela.domain.validation.reservation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.time.LocalDateTime;

@Slf4j
public class BeginBeforeEndValidator implements ConstraintValidator<EarlierDate, Object> {
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    String earlier;
    String later;
    String message;

    @Override
    public void initialize(EarlierDate constraintAnnotation) {
        earlier = constraintAnnotation.earlier();
        later = constraintAnnotation.later();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        log.info("Earlier:"+earlier);
        log.info("Earlier:"+later);
        if(earlier!=null && later!=null){
            var begin = (LocalDateTime)PARSER.parseExpression(earlier).getValue(value);
            var end = (LocalDateTime)PARSER.parseExpression(later).getValue(value);
            log.info("Begin:"+begin.toString());
            log.info("End:"+end);
            if(end != null && begin.isBefore(end)){
                return true;
            }
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}
