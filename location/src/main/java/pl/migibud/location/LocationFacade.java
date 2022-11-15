package pl.migibud.location;

import lombok.RequiredArgsConstructor;
import pl.migibud.dto.CreateLocationRequest;
import pl.migibud.dto.LocationDto;

@RequiredArgsConstructor
public class LocationFacade {
    private final LocationRepository locationRepository;

    public LocationDto registerLocation(CreateLocationRequest request) {
        Location location = Location.of(request);
        return locationRepository.save(location).toDto();
    }
}
