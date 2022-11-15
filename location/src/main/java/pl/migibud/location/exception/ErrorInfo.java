package pl.migibud.location.exception;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
class ErrorInfo {
	private final int code;
	private final Instant timestamp;
	private final List<String> message;
}
