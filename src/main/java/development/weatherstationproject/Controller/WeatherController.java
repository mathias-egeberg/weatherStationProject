package development.weatherstationproject.Controller;


import development.weatherstationproject.Services.WeatherService;
import development.weatherstationproject.WeatherDataDTO;
import development.weatherstationproject.entity.WeatherData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/latest")
    public ResponseEntity<Map<String, Object>> getLatestWeather() {
        WeatherData latestWeather = service.getLatestWeather();

        if (latestWeather == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("temperature", latestWeather.getTemperature());
        response.put("humidity", latestWeather.getHumidity());
        response.put("pressure", latestWeather.getPressure());
        response.put("windSpeed", latestWeather.getWindSpeed());
        response.put("windDirection", latestWeather.getWindDirection());
        response.put("timestamp", latestWeather.getTimestamp()); // Include timestamp

        return ResponseEntity.ok(response);
    }

    @GetMapping("/historical")
    public ResponseEntity<List<WeatherDataDTO>> getHistoricalData(
            @RequestParam String timeframe,
            @RequestParam String type) {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate;

        if ("week".equalsIgnoreCase(timeframe)) {
            startDate = endDate.minusDays(7); // Last 7 days
        } else if ("month".equalsIgnoreCase(timeframe)) {
            startDate = endDate.minusDays(30); // Last 30 days
        } else {
            throw new IllegalArgumentException("Invalid timeframe: " + timeframe);
        }

        List<WeatherDataDTO> data = service.getHistoricalData(startDate, endDate, type);
        return ResponseEntity.ok(data);
    }

}
