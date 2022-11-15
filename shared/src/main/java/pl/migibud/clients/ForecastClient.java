package pl.migibud.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.migibud.dto.ForecastClientDto;

import java.util.List;

@FeignClient(name = "forecast-client",url = "https://api.openweathermap.org/data/2.5/onecall")
public interface ForecastClient {
    @GetMapping
    ForecastClientDto getForecastFromClient(
            @RequestParam("lat") Double latitude,
            @RequestParam("lon") Double longitude,
            @RequestParam("units") String units,
            @RequestParam("exclude") List<String> exclude,
            @RequestParam("appid") String appid
            );
}
