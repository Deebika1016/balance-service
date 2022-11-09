package com.maveric.balanceservice.exception;

import com.maveric.balanceservice.dto.ErrorDto;
import feign.FeignException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.maveric.balanceservice.constants.Constants.*;

@RestControllerAdvice
public class ExceptionControllerAdvisor {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExceptionControllerAdvisor.class);
    String exceptionString="";
    @ExceptionHandler(BalanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static final ErrorDto handleBalanceNotFoundException(BalanceNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BALANCE_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
    @ExceptionHandler(InvalidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static final ErrorDto invalidException(InvalidException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BALANCE_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        errorDto.setCode(BAD_REQUEST_CODE);
        errorDto.setMessage(BAD_REQUEST_MESSAGE);
        errorDto.setErrors(errors);
        return errorDto;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorDto handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(METHOD_NOT_ALLOWED_CODE);
        errorDto.setMessage(METHOD_NOT_ALLOWED_MESSAGE);
        return errorDto;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        if(ex.getMessage().contains("com.maveric.balanceservice.enumeration.Currency"))//NOSONAR
            errorDto.setMessage(INVALID_INPUT_TYPE);
        else
            errorDto.setMessage(HTTPMESSAGENOTREADABLEEXCEPTION_MESSAGE);
        return errorDto;
    }
    @ExceptionHandler(BalanceAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDto handleBalanceAlreadyExistException(BalanceAlreadyExistException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        errorDto.setMessage(exception.getMessage());
        exceptionString = exception.getMessage();
        log.error("{}->{}",BAD_REQUEST_CODE,exceptionString);
        return errorDto;
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorDto handleOtherHttpException(Exception exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(INTERNAL_SERVER_ERROR_CODE);
        errorDto.setMessage(INTERNAL_SERVER_ERROR_MESSAGE);
        exceptionString = exception.getMessage();
        log.error("{} {}-> {}",INTERNAL_SERVER_ERROR_CODE,INTERNAL_SERVER_ERROR_MESSAGE,exceptionString);
        return errorDto;
    }
    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDto handleHttpFeignException(
            FeignException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(SERVICE_UNAVAILABLE_CODE);
        errorDto.setMessage(SERVICE_UNAVAILABLE_MESSAGE);
        exceptionString = ex.getMessage();
        log.error("{} -> {} -> {}",SERVICE_UNAVAILABLE_CODE,SERVICE_UNAVAILABLE_MESSAGE,exceptionString);
        return errorDto;
    }

}
