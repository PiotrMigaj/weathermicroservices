package pl.migibud.forecast;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.migibud.dto.CreateForecastRequest;
import pl.migibud.dto.ForecastDto;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/forecasts")
@RequiredArgsConstructor
class ForecastController {

    private final ForecastFacade forecastFacade;

    @PostMapping
    ResponseEntity<ForecastDto> getForecast(@RequestBody @Valid CreateForecastRequest request){
        return ResponseEntity.ok(forecastFacade.getForecast(request));
    }
}
