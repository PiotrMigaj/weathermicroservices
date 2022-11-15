package pl.migibud.forecast;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.default")
class ForecastInitialConfiguration {
    private String appid;
    private String units;
    private List<String> exclude;
}
