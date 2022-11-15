package pl.migibud.forecast;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.migibud.clients.ForecastClient;
import pl.migibud.clients.LocationClient;

import java.time.Clock;

@Configuration
class ForecastConfiguration {

    @Bean
    ForecastFacade forecastFacade(
            ForecastRepository forecastRepository,
            LocationClient locationClient,
            ForecastClient forecastClient,
            ForecastInitialConfiguration forecastInitialConfiguration
    ) {
        return new ForecastFacade(
                forecastRepository,
                locationClient,
                forecastClient,
                forecastInitialConfiguration,
                Clock.systemDefaultZone()
        );
    }

    @Bean
    ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }


}
