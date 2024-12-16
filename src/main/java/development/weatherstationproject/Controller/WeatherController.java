package development.weatherstationproject.Controller;


import development.weatherstationproject.Services.WeatherService;
import development.weatherstationproject.entity.WeatherData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/latest")
    public WeatherData getLatestWeather() {
        return service.getLatestWeather();
    }

}
