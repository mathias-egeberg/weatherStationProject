document.addEventListener("DOMContentLoaded", () => {
    const apiUrl = "/api/weather/latest";

    const updateWeather = async () => {
        try {
            const response = await fetch(apiUrl);
            if (!response.ok) throw new Error("Failed to fetch weather data");

            const data = await response.json();
            const now = new Date();
            const formattedTime = now.toLocaleTimeString("no-NO", {
                hour: "2-digit",
                minute: "2-digit",
            });

            // Update main weather data
            document.getElementById("temperature").textContent = `${data.temperature}°C`;
            document.getElementById("humidity").textContent = `${data.humidity}%`;
            document.getElementById("pressure").textContent = `${data.pressure} hPa`;
            document.getElementById("windSpeed").textContent = `${data.windSpeed} m/s`;

            const directionTranslations = {
                N: "Nord",
                NNE: "Nord-Nordøst",
                NE: "Nordøst",
                ENE: "Øst-Nordøst",
                E: "Øst",
                ESE: "Øst-Sørøst",
                SE: "Sørøst",
                SSE: "Sør-Sørøst",
                S: "Sør",
                SSW: "Sør-Sørvest",
                SW: "Sørvest",
                WSW: "Vest-Sørvest",
                W: "Vest",
                WNW: "Vest-Nordvest",
                NW: "Nordvest",
                NNW: "Nord-Nordvest",
            };
            const windDirectionNorwegian =
                directionTranslations[data.windDirection] || data.windDirection;

            document.getElementById("windDirection").textContent = windDirectionNorwegian;

            // Update compass arrow rotation
            const directions = {
                N: 0,
                NE: 45,
                E: 90,
                SE: 135,
                S: 180,
                SW: 225,
                W: 270,
                NW: 315,
            };
            const rotation = directions[data.windDirection] || 0;
            document.getElementById("compassArrow").style.transform = `translate(-50%, -50%) rotate(${rotation}deg)`;

            // Update "Last updated" time
            document.getElementById("last-updated").textContent = `Sist oppdatert: ${formattedTime}`;
        } catch (error) {
            console.error("Error updating weather data:", error);
        }
    };

    // Update weather immediately and every 60 seconds
    updateWeather();
    setInterval(updateWeather, 10000); // Refresh every minute
});
