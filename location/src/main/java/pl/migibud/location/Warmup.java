package pl.migibud.location;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.migibud.dto.CreateLocationRequest;

@Component
@RequiredArgsConstructor
class Warmup {

    private final LocationRepository locationRepository;
    private final LocationFacade locationFacade;

    @EventListener(ContextRefreshedEvent.class)
    void init(){
        if (locationRepository.count()==0){
            CreateLocationRequest wroclaw = CreateLocationRequest.builder()
                    .city("Wroclaw ")
                    .country("Poland")
                    .region("Lower Silesia")
                    .latitude(51.1000000)
                    .longitude(17.0333300)
                    .build();

            CreateLocationRequest poznan = CreateLocationRequest.builder()
                    .city("Poznan")
                    .country("Poland")
                    .region("Greater Poland")
                    .latitude(52.4069200)
                    .longitude(16.9299300)
                    .build();

            CreateLocationRequest krakow = CreateLocationRequest.builder()
                    .city("Krakow")
                    .country("Poland")
                    .region("Lesser Poland")
                    .latitude(50.0614300)
                    .longitude(19.9365800)
                    .build();

            CreateLocationRequest zakopane = CreateLocationRequest.builder()
                    .city("Zakopane")
                    .country("Poland")
                    .region("Lesser Poland")
                    .latitude(49.2989900)
                    .longitude(19.9488500)
                    .build();

            CreateLocationRequest warsaw = CreateLocationRequest.builder()
                    .city("Warsaw")
                    .country("Poland")
                    .region("Masovian")
                    .latitude(52.2297700)
                    .longitude(21.0117800)
                    .build();

            locationFacade.registerLocation(wroclaw);
            locationFacade.registerLocation(poznan);
            locationFacade.registerLocation(krakow);
            locationFacade.registerLocation(zakopane);
            locationFacade.registerLocation(warsaw);
        }
    }
}
