package pl.migibud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "pl.migibud.clients"
)
public class ForecastApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForecastApplication.class,args);
    }
}
