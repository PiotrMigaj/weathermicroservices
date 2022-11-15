package pl.migibud.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.migibud.dto.CreateLocationRequest;
import pl.migibud.dto.LocationDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String region;
    private String country;
    private Double longitude;
    private Double latitude;
    private LocalDateTime createdDate;

    Location(String city, String region, String country, Double longitude, Double latitude, LocalDateTime createdDate) {
        this.city = city;
        this.region = region;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.createdDate = createdDate;
    }

    public static Location of(CreateLocationRequest request){
        return new Location(
                request.getCity(),
                request.getRegion(),
                request.getCountry(),
                request.getLongitude(),
                request.getLatitude(),
                LocalDateTime.now()
        );
    }

    public LocationDto toDto(){
        return new LocationDto(
                id,
                city,
                region,
                country,
                longitude,
                latitude
        );
    }

}
