package pl.migibud.forecast;

import org.springframework.stereotype.Component;
import pl.migibud.dto.CreateForecastRequest;
import pl.migibud.dto.ForecastClientDto;

import java.time.LocalDate;

@Component
class ForecastFactory {
    static Forecast of(CreateForecastRequest request, ForecastClientDto.SingleForecastDto singleForecastDto){
        return new Forecast(
                singleForecastDto.getTemperature().getDay(),
                singleForecastDto.getPressure(),
                singleForecastDto.getHumidity(),
                singleForecastDto.getWindSpeed(),
                singleForecastDto.getWindDeg(),
                LocalDate.now(),
                request.getForecastDate(),
                request.getLocationId()
        );
    }
}
