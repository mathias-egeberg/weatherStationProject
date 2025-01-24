package development.weatherstationproject;

import java.time.LocalDateTime;

public class WeatherDataDTO {
    private LocalDateTime timestamp;
    private Float value;

    public WeatherDataDTO(LocalDateTime timestamp, Float value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
