package pl.migibud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.migibud.projection.LocationView;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto implements LocationView {
    private Long id;
    private String city;
    private String region;
    private String country;
    private Double longitude;
    private Double latitude;
}
