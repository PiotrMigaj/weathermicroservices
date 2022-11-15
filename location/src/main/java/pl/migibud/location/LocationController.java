package pl.migibud.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.migibud.dto.CreateLocationRequest;
import pl.migibud.dto.LocationDto;
import pl.migibud.location.exception.LocationError;
import pl.migibud.location.exception.LocationException;
import pl.migibud.projection.LocationView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
class LocationController {

    private final LocationFacade locationFacade;
    private final LocationQueryRepository locationQueryRepository;

    @PostMapping
    ResponseEntity<LocationDto> registerLocation(@RequestBody @Valid CreateLocationRequest request){
        LocationDto locationDto = locationFacade.registerLocation(request);
        return ResponseEntity.ok(locationDto);
    }

    @GetMapping
    ResponseEntity<Page<LocationView>> getAllLocations(
            @RequestParam(required = false,defaultValue = "0") Integer page,
            @RequestParam(required = false,defaultValue = "10") Integer size
    ){
        return ResponseEntity.ok(locationQueryRepository.findAllBy(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    ResponseEntity<LocationView> getLocationById(@PathVariable Long id){
        LocationView locationView = locationQueryRepository.findAllById(id)
                .orElseThrow(() -> new LocationException(LocationError.LOCATION_NOT_FOUND));
        return ResponseEntity.ok(locationView);
    }


}
