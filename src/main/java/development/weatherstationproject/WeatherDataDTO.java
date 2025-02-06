package development.weatherstationproject;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WeatherDataDTO {
    private LocalDateTime timestamp;
    private Float value;

    public WeatherDataDTO(LocalDateTime timestamp, Float value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
