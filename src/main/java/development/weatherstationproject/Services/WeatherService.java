package development.weatherstationproject.Services;


import development.weatherstationproject.Repository.WeatherRepository;
import development.weatherstationproject.WeatherDataDTO;
import development.weatherstationproject.WeatherDataRepository;
import development.weatherstationproject.entity.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final WeatherDataRepository repository;

    public WeatherService(WeatherDataRepository repository) {
        this.repository = repository;
    }

    public WeatherData getLatestWeather() {
        return repository.findTopByOrderByTimestampDesc();
    }


    @Autowired
    private WeatherRepository weatherRepository;

    public List<WeatherDataDTO> getHistoricalData(LocalDateTime startDate, LocalDateTime endDate, String type) {
        switch (type.toLowerCase()) {
            case "temperature":
                return weatherRepository.findTemperatureDataBetweenDates(startDate, endDate);
            case "humidity":
                return weatherRepository.findHumidityDataBetweenDates(startDate, endDate);
            case "pressure":
                return weatherRepository.findPressureDataBetweenDates(startDate, endDate);
            default:
                throw new IllegalArgumentException("Invalid data type: " + type);
        }
    }

    private Float getValueForType(WeatherData data, String type) {
        return switch (type) {
            case "temperature" -> data.getTemperature();
            case "humidity" -> data.getHumidity();
            case "pressure" -> data.getPressure();
            case "windSpeed" -> data.getWindSpeed();
            default -> throw new IllegalArgumentException("Invalid data type: " + type);
        };
    }




}
