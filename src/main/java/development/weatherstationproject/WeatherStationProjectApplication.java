package development.weatherstationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "development.weatherstationproject")
public class WeatherStationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherStationProjectApplication.class, args);
    }

}
