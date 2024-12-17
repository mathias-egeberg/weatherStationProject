package development.weatherstationproject.Services;


import development.weatherstationproject.WeatherDataRepository;
import development.weatherstationproject.entity.WeatherData;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final WeatherDataRepository repository;

    public WeatherService(WeatherDataRepository repository) {
        this.repository = repository;
    }

    public WeatherData getLatestWeather() {
        return repository.findTopByOrderByTimestampDesc();
    }

}
