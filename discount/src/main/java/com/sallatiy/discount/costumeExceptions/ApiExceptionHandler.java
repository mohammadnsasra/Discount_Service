package com.sallatiy.discount.costumeExceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(ApiBaseException.class)
    public ResponseEntity<ErrorDetails> handleApiExceptions(ApiBaseException exception,WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,exception.getStatusCode());
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ValidationError validationError = new ValidationError();
        validationError.setUri(request.getDescription(false));
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for(FieldError f: fieldErrors) {
            validationError.addError(f.getDefaultMessage());
        }


        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }
}

