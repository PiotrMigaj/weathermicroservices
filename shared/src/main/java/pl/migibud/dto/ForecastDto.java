package pl.migibud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ForecastDto {
    private Long id;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private int pressure;
    private String windDirection;
    private Long locationId;
}
