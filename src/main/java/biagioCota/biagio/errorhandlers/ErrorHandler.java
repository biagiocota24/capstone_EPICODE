package biagioCota.biagio.errorhandlers;

import biagioCota.biagio.exceptions.DuplicateEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ErrorResponse duplicateEmailException(
            DuplicateEmailException ex, WebRequest request
    ) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now(), request.getDescription(false).replace("uri=", ""));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse validationException(
            MethodArgumentNotValidException ex, WebRequest request
    ) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, LocalDateTime.now(), request.getDescription(false).replace("uri=", ""));
    }
}
