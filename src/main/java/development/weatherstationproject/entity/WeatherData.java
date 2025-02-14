package development.weatherstationproject.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stadion_readings")
public class WeatherData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;


    private Float temperature;
    private Float humidity;
    private Float pressure;
    private Float windSpeed;
    private String windDirection;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Float getTemperature() { return temperature; }
    public void setTemperature(Float temperature) { this.temperature = temperature; }

    public Float getHumidity() { return humidity; }
    public void setHumidity(Float humidity) { this.humidity = humidity; }

    public Float getPressure() { return pressure; }
    public void setPressure(Float pressure) { this.pressure = pressure; }

    public Float getWindSpeed() { return windSpeed; }
    public void setWindSpeed(Float windSpeed) { this.windSpeed = windSpeed; }

    public String getWindDirection() { return windDirection; }
    public void setWindDirection(String windDirection) { this.windDirection = windDirection; }


}
