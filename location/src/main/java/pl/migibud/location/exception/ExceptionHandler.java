package pl.migibud.location.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Collections;

@RestControllerAdvice
class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = LocationException.class)
    ResponseEntity<ErrorInfo> handleLocationException(LocationException e) {
        HttpStatus httpStatus = null;
        if (LocationError.LOCATION_NOT_FOUND.equals(e.getLocationError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(httpStatus).body(
                new ErrorInfo(
                        httpStatus.value(),
                        Instant.now(),
                        Collections.singletonList(e.getLocationError().getMessage())
                )
        );
    }

}
