package pl.migibud.forecast;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.migibud.clients.ForecastClient;
import pl.migibud.clients.LocationClient;
import pl.migibud.dto.CreateForecastRequest;
import pl.migibud.dto.ForecastDto;
import pl.migibud.dto.LocationDto;
import pl.migibud.forecast.exception.ForecastException;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(SpringExtension.class)
class ForecastFacadeTest {

    public static final LocalDate LOCAL_DATE_NOW = LocalDate.of(2022,11,21);

    @TestConfiguration
    static class ForecastFacadeTestConfiguration{
        @Bean
        ForecastFacade forecastFacade(
                ForecastRepository forecastRepository,
                LocationClient locationClient,
                ForecastClient forecastClient,
                ForecastInitialConfiguration forecastInitialConfiguration,
                Clock clock
        ) {
            return new ForecastFacade(
                    forecastRepository,
                    locationClient,
                    forecastClient,
                    forecastInitialConfiguration,
                    clock
            );
        }
    }

    @Autowired
    ForecastFacade forecastFacade;
    @MockBean
    ForecastInitialConfiguration forecastInitialConfiguration;
    @MockBean
    LocationClient locationClient;
    @MockBean
    ForecastClient forecastClient;
    @MockBean
    ForecastRepository forecastRepository;
    @MockBean
    Clock clock;

    private Clock fixedClock;

    @BeforeEach
    void setUp(){
        fixedClock = Clock.fixed(LOCAL_DATE_NOW.atStartOfDay(ZoneId.systemDefault()).toInstant(),ZoneId.systemDefault());
        Mockito.when(clock.getZone()).thenReturn(fixedClock.getZone());
        Mockito.when(clock.instant()).thenReturn(fixedClock.instant());
    }

    @Test
    void givenPreviousDateOfForecast_whenGetForecast_thenThrowException(){
        //given
        CreateForecastRequest request = new CreateForecastRequest(1L, LocalDate.of(2022,11,20));
        //when
        //then
        assertThatThrownBy(()->forecastFacade.getForecast(request))
                .isNotNull()
                .isInstanceOf(ForecastException.class);
    }

    @Test
    void givenDateOfForecastAfterValidForecastFutureDate_whenGetForecast_thenThrowException(){
        //given
        CreateForecastRequest request = new CreateForecastRequest(1L, LocalDate.of(2022,11,29));
        //when
        //then
        assertThatThrownBy(()->forecastFacade.getForecast(request))
                .isNotNull()
                .isInstanceOf(ForecastException.class);
    }

    @Test
    void givenForecastRequestWithValidForecastDateAndLocationId_whenOneDayAheadForecastAlreadyStoredInDb_thenReturnCachedForecast(){
        //given
        CreateForecastRequest request = new CreateForecastRequest(1L, LocalDate.of(2022,11,25));
        Mockito.when(locationClient.getLocationById(anyLong())).thenReturn(new LocationDto(1L,
                "Wroclaw",
                "Lower Silesia",
                "Poland",
                51.1000000,
                17.0333300));
        Mockito.when(forecastRepository.findByForecastDateAndLocationId(any(),any())).thenReturn(
                List.of(new Forecast(1L,8.26,1000,70,3.25,45,LocalDate.of(2022,11,20),LocalDate.of(2022,11,25),1L))
        );
        //when
        ForecastDto forecast = forecastFacade.getForecast(request);
        //then
        assertThat(forecast).isNotNull();
        assertThat(forecast.getId()).isEqualTo(1L);
        assertThat(forecast.getTemperature()).isCloseTo(8.26, Percentage.withPercentage(1));
    }

    @Test
    void givenForecastRequestWithValidForecastDateAndLocationId_whenForecastWithSameCreateAndForecastDateForLocationAlreadyStoredInDb_thenReturnCachedForecast(){
        //given
        CreateForecastRequest request = new CreateForecastRequest(1L, LocalDate.of(2022,11,25));
        Mockito.when(locationClient.getLocationById(anyLong())).thenReturn(new LocationDto(1L,
                "Wroclaw",
                "Lower Silesia",
                "Poland",
                51.1000000,
                17.0333300));
        Mockito.when(forecastRepository.findByForecastDateAndLocationId(any(),any())).thenReturn(
                List.of(new Forecast(1L,8.26,1000,70,3.25,45,LocalDate.of(2022,11,21),LocalDate.of(2022,11,25),1L))
        );
        //when
        ForecastDto forecast = forecastFacade.getForecast(request);
        //then
        assertThat(forecast).isNotNull();
        assertThat(forecast.getId()).isEqualTo(1L);
        assertThat(forecast.getTemperature()).isCloseTo(8.26, Percentage.withPercentage(1));
    }

}