package pl.migibud.location.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LocationError {

	LOCATION_NOT_FOUND("Location does not exists");

	private final String message;
}
