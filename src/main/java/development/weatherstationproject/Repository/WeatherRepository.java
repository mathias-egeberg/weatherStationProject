package development.weatherstationproject.Repository;

import development.weatherstationproject.WeatherDataDTO;
import development.weatherstationproject.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {

    @Query("SELECT new development.weatherstationproject.WeatherDataDTO(w.timestamp, w.temperature) " +
            "FROM WeatherData w WHERE w.timestamp BETWEEN :startDate AND :endDate")
    List<WeatherDataDTO> findTemperatureDataBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT new development.weatherstationproject.WeatherDataDTO(w.timestamp, w.humidity) " +
            "FROM WeatherData w WHERE w.timestamp BETWEEN :startDate AND :endDate")
    List<WeatherDataDTO> findHumidityDataBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT new development.weatherstationproject.WeatherDataDTO(w.timestamp, w.pressure) " +
            "FROM WeatherData w WHERE w.timestamp BETWEEN :startDate AND :endDate")
    List<WeatherDataDTO> findPressureDataBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);


    // Get latest weather data
    WeatherData findTopByOrderByTimestampDesc();

    // Queries for historical data
    List<WeatherData> findByTimestampBetween(LocalDateTime start, LocalDateTime end);



}
