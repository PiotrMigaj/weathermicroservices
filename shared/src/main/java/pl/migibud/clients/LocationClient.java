package pl.migibud.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.migibud.dto.LocationDto;

@FeignClient("location-service")
public interface LocationClient {

    @GetMapping(path = "/api/locations/{id}")
    LocationDto getLocationById(@PathVariable(value = "id") Long id);
}
