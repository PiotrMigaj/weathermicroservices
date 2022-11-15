package pl.migibud.location;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LocationConfiguration {

    @Bean
    LocationFacade locationFacade(LocationRepository locationRepository){
        return new LocationFacade(locationRepository);
    }
}
