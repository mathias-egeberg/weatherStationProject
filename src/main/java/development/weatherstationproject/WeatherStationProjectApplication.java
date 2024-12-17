package development.weatherstationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "development.weatherstationproject.Repository")
public class WeatherStationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherStationProjectApplication.class, args);
    }

}
