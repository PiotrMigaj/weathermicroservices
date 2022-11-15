package pl.migibud.forecast;

import lombok.*;
import pl.migibud.dto.ForecastDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double temperature;
    private int pressure;
    private int humidity;
    private double windSpeed;
    private int windDirection;
    private LocalDate createDate;
    private LocalDate forecastDate;
    private Long locationId;

    public Forecast(double temperature, int pressure, int humidity, double windSpeed, int windDirection, LocalDate createDate, LocalDate forecastDate, Long locationId) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.createDate = createDate;
        this.forecastDate = forecastDate;
        this.locationId = locationId;
    }

    public ForecastDto toDto(){
        return new ForecastDto(
                id,
                temperature,
                humidity,
                windSpeed,
                pressure,
                mapWindDegToCompassDirection(windDirection),
                locationId
        );
    }

    private String mapWindDegToCompassDirection(int windDeg){
        String[] compassDir = {"N","NE","E","SE","S","SW","W","NW","N"};
        int index = (int)Math.round(windDeg/45.0);
        return compassDir[index];
    }
}
