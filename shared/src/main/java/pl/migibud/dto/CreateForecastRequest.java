package pl.migibud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateForecastRequest {
    @NotNull
    private Long locationId;
    @NotNull
    private LocalDate forecastDate;
}
