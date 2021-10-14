package net.erickcaron.mybudgetapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value= ResourceException.class)
    public ResponseEntity<ErrorInfo> handleException(ResourceException resourceException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInfo(resourceException.getResourceError().getMessage()));
    }
}
