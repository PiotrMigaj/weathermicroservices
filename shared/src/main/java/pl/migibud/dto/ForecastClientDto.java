package pl.migibud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastClientDto {

    private List<SingleForecastDto> daily;

    @Data
    public static class SingleForecastDto {
        @JsonProperty("dt")
        private Long timestamp;
        @JsonProperty("temp")
        private TemperatureDto temperature;
        private Integer pressure;
        private Integer humidity;
        @JsonProperty("wind_speed")
        private Double windSpeed;
        @JsonProperty("wind_deg")
        private Integer windDeg;

        @Data
        public static class TemperatureDto {
            private Double day;
        }
    }
}
