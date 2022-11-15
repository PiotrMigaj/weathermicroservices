package pl.migibud.forecast.exception;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
class ErrorInfo {
	private final int code;
	private final Instant timestamp;
	private final List<String> message;
}
