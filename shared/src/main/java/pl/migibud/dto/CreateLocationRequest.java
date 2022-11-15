package pl.migibud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationRequest {
    @NotNull
    private String city;
    @NotNull
    private String region;
    @NotNull
    private String country;
    @NotNull
    private Double longitude;
    @NotNull
    private Double latitude;
}
