package pl.migibud.location.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LocationException extends RuntimeException{

	private LocationError locationError;
}
