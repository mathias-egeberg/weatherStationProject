document.addEventListener("DOMContentLoaded", () => {
    const apiUrl = "/api/weather/latest";

    const updateWeather = async () => {
        try {
            const response = await fetch(apiUrl);
            if (!response.ok) throw new Error("Failed to fetch weather data");

            const data = await response.json();

            // Update HTML elements with fetched data
            document.getElementById("temperature").textContent = `${data.temperature}°C`;
            document.getElementById("humidity").textContent = `${data.humidity}%`;
            document.getElementById("pressure").textContent = `${data.pressure} hPa`;
            document.getElementById("windSpeed").textContent = `${data.windSpeed} m/s`;
            document.getElementById("windDirection").textContent = data.windDirection;

            // Map wind direction to degrees for rotation
            const directions = { N: 0, NNE: 22.5, NE: 45, ENE: 67.5, E: 90, ESE: 112.5, SE: 135, SSE: 157.5, S: 180, SSW: 202.5, SW: 225, WSW: 247.5, W: 270, WNW: 292.5, NW: 315, NNW: 337.5 };
            const rotation = directions[data.windDirection] || 0;

            // Rotate the arrow
            document.getElementById("windArrow").style.transform = `translate(-50%, -50%) rotate(${rotation}deg)`;
        } catch (error) {
            console.error("Error updating weather data:", error);
        }
    };

    // Update weather immediately and every 60 seconds
    updateWeather();
    setInterval(updateWeather, 10000); // Refresh every minute
});
