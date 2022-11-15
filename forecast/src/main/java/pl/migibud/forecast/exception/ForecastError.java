package pl.migibud.forecast.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ForecastError {
	LOCATION_NOT_FOUND("Location does not exists"),
	FORECAST_WITH_REQUESTED_DATE_NOT_FOUND("Forecast with requested date not found"),
	FORECAST_DATE_BEFORE_NOW("Can not get weather forecast with previous date"),
	FORECAST_DATE_AFTER_7_DAYS_AHEAD("Weather forecast is limited to 7 days ahead");

	private final String message;
}
