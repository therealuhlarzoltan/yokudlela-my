package hu.yokudlela.yokudlela.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Component
@Slf4j
public class ValidationErrorHandler extends ResponseEntityExceptionHandler {

    @Autowired
    HttpServletRequest req;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> businessExceptionHandle(Exception ex, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
        ApiException error = new ApiException(req.getRequestURI(),"error.business");
        error.getErrors().add(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Object> unexpectedTypeExceptionHandle(Exception ex, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
        ApiException error = new ApiException(req.getRequestURI(),"error.business");
        error.getErrors().add(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataValidationException.class, ConversionFailedException.class})
    public ResponseEntity<Object> dataValidationExceptionHandle(Exception ex, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
        ApiException error = new ApiException(req.getRequestURI(),"error.business");
        error.getErrors().add(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> HandleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
       ApiException error = new ApiException(req.getRequestURI(),"error.validation");
        for(var msg: ex.getConstraintViolations()){
            error.getErrors().add(msg.getMessage());
        }

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ResponseBody
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
        ApiException error = new ApiException(req.getRequestURI(),"error.validation");

        ex.getBindingResult().getAllErrors().forEach((ObjectError err) -> error.getErrors().add(
                (err.getDefaultMessage().indexOf(' ') > 0) ? "error.validation.MethodArgumentNotValid" : err.getDefaultMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);
        ApiException error = new ApiException(req.getRequestURI(),"error.validation");

        error.getErrors().add(
                (ex.getLocalizedMessage().indexOf(' ') > 0)? "error.validation.HttpMessageNotReadable" : ex.getLocalizedMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
