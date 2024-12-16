package development.weatherstationproject.Repository;


import development.weatherstationproject.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    WeatherData findTopByOrderByTimestampDesc(); // Fetch the latest weather data
}
