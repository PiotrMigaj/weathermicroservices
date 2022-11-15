package pl.migibud.forecast.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Collections;

@RestControllerAdvice
class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ForecastException.class)
    ResponseEntity<ErrorInfo> handleLocationException(ForecastException e) {
        HttpStatus httpStatus = null;
        if (ForecastError.FORECAST_DATE_BEFORE_NOW.equals(e.getForecastError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if (ForecastError.FORECAST_DATE_AFTER_7_DAYS_AHEAD.equals(e.getForecastError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if (ForecastError.LOCATION_NOT_FOUND.equals(e.getForecastError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(
                new ErrorInfo(
                        httpStatus.value(),
                        Instant.now(),
                        Collections.singletonList(e.getForecastError().getMessage())
                )
        );
    }

}
