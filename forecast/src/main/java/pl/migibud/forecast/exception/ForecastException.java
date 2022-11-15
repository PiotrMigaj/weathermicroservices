package pl.migibud.forecast.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ForecastException extends RuntimeException{

	private ForecastError forecastError;
}
