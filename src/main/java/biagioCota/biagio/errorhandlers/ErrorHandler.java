package biagioCota.biagio.errorhandlers;

import biagioCota.biagio.exceptions.DuplicateEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ErrorResponse duplicateEmailException(
            DuplicateEmailException ex, WebRequest request
    ) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validationException(
            MethodArgumentNotValidException ex, WebRequest request
    ) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
                error -> error.getField(),
                error -> error.getDefaultMessage(),
                (existing, replacement) -> existing
        ));
        if (!errors.isEmpty()) {
            System.out.println("❌ Errori di validazione trovati:");
            errors.forEach((field, message) ->
                    System.out.println("   --" + field + "; " + message));
        }
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        errorResponse.setErrors(errors);
        return errorResponse;

    }
}
