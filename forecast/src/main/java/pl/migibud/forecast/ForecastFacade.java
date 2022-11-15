package pl.migibud.forecast;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pl.migibud.clients.ForecastClient;
import pl.migibud.clients.LocationClient;
import pl.migibud.dto.CreateForecastRequest;
import pl.migibud.dto.ForecastClientDto;
import pl.migibud.dto.ForecastDto;
import pl.migibud.dto.LocationDto;
import pl.migibud.forecast.exception.ForecastError;
import pl.migibud.forecast.exception.ForecastException;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ForecastFacade {

    private static final int DAYS_TO_ADD_TO_GET_FUTURE_FORECAST_DATE = 7;
    public static final int DAYS_OFFSET = 1;
    private final ForecastRepository forecastRepository;
    private final LocationClient locationClient;
    private final ForecastClient forecastClient;
    private final ForecastInitialConfiguration forecastInitialConfiguration;
    private final Clock clock;
    @Transactional
    public ForecastDto getForecast(CreateForecastRequest request) {
        validateForecastRequestDate(request);

        LocationDto locationDto = locationClient.getLocationById(request.getLocationId());

        Optional<Forecast> optionalForecastOneDayCache = forecastRepository.findByForecastDateAndLocationId(request.getForecastDate(), request.getLocationId()).stream()
                .filter(forecast -> ChronoUnit.DAYS.between(LocalDate.now(clock),forecast.getCreateDate())<= DAYS_OFFSET)
                .findFirst();
        if (optionalForecastOneDayCache.isPresent()){
            return optionalForecastOneDayCache.get().toDto();
        }

        Optional<Forecast> optionalForecast = forecastRepository.findByCreateDateAndForecastDateAndLocationId(LocalDate.now(clock), request.getForecastDate(), request.getLocationId());
        if (optionalForecast.isPresent()){
            return optionalForecast.get().toDto();
        }

        ForecastClientDto.SingleForecastDto singleForecastDto = getSingleForecastDto(request, locationDto);
        Forecast forecast = ForecastFactory.of(request, singleForecastDto);

        return forecastRepository.save(forecast).toDto();
    }

    private ForecastClientDto.SingleForecastDto getSingleForecastDto(CreateForecastRequest request, LocationDto locationDto) {
        ForecastClientDto forecastFromClient = forecastClient.getForecastFromClient(
                locationDto.getLatitude(),
                locationDto.getLongitude(),
                forecastInitialConfiguration.getUnits(),
                forecastInitialConfiguration.getExclude(),
                forecastInitialConfiguration.getAppid()
        );
        return forecastFromClient.getDaily().stream()
                .filter(singleForecastDto -> {
                    LocalDate localDate = Instant.ofEpochSecond(singleForecastDto.getTimestamp()).atZone(ZoneId.systemDefault()).toLocalDate();
                    return localDate.isEqual(request.getForecastDate());
                })
                .findFirst()
                .orElseThrow(() -> new ForecastException(ForecastError.FORECAST_WITH_REQUESTED_DATE_NOT_FOUND));
    }

    private void validateForecastRequestDate(CreateForecastRequest request) {
        LocalDate forecastDate = request.getForecastDate();
        if (forecastDate.isBefore(LocalDate.now(clock))){
            throw new ForecastException(ForecastError.FORECAST_DATE_BEFORE_NOW);
        }
        if (forecastDate.isAfter(LocalDate.now(clock).plusDays(DAYS_TO_ADD_TO_GET_FUTURE_FORECAST_DATE))){
            throw new ForecastException(ForecastError.FORECAST_DATE_AFTER_7_DAYS_AHEAD);
        }
    }
}
