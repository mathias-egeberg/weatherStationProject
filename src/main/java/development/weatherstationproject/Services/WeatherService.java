package development.weatherstationproject.Services;


import development.weatherstationproject.Repository.WeatherRepository;
import development.weatherstationproject.WeatherDataDTO;
import development.weatherstationproject.entity.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    // ✅ Get the latest weather data
    public WeatherData getLatestWeather() {
        return weatherRepository.findTopByOrderByTimestampDesc();
    }

    // ✅ Get historical data based on type
    public List<WeatherDataDTO> getHistoricalData(LocalDateTime startDate, LocalDateTime endDate, String type) {
        return switch (type.toLowerCase()) {
            case "temperature" -> weatherRepository.findTemperatureDataBetweenDates(startDate, endDate);
            case "humidity" -> weatherRepository.findHumidityDataBetweenDates(startDate, endDate);
            case "pressure" -> weatherRepository.findPressureDataBetweenDates(startDate, endDate);
            default -> throw new IllegalArgumentException("Invalid data type: " + type);
        };
    }

    // ✅ Store new weather data (used by Raspberry Pi)
    @Transactional
    public void addWeatherData(WeatherData data) {
        WeatherData weatherData = new WeatherData();
        weatherData.setTemperature(data.getTemperature());
        weatherData.setHumidity(data.getHumidity());
        weatherData.setPressure(data.getPressure());
        weatherData.setWindSpeed(data.getWindSpeed());
        weatherData.setWindDirection(data.getWindDirection());
        weatherData.setTimestamp(data.getTimestamp() != null ? data.getTimestamp() : Instant.now());

        weatherRepository.save(weatherData);
    }
}

