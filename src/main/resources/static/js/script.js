document.addEventListener("DOMContentLoaded", () => {
    const apiUrl = "/api/weather/latest";
    const lastUpdatedElement = document.getElementById("last-updated");

    const updateWeather = async () => {
        try {
            const response = await fetch(apiUrl);
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

            const data = await response.json();

            // Ensure the required fields are present
            const { temperature, humidity, pressure, timestamp } = data;
            if (temperature == null || humidity == null || pressure == null || !timestamp) {
                throw new Error("Missing required weather data (temperature, humidity, pressure, timestamp).");
            }

            // Update weather data in the DOM
            document.getElementById("temperature").textContent = `${temperature}°C`;
            document.getElementById("humidity").textContent = `${humidity}%`;
            document.getElementById("pressure").textContent = `${pressure} hPa`;

            // Optional: Handle wind data if present
            const windSpeed = data.windSpeed ?? "--";
            const windDirection = data.windDirection ?? "--";

            document.getElementById("windSpeed").textContent = `${windSpeed} m/s`;

            // Map wind direction to Norwegian translations
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
                directionTranslations[windDirection] || windDirection;

            document.getElementById("windDirection").textContent = windDirectionNorwegian;

            // Update compass arrow rotation only if windDirection is valid and compassArrow exists
            const compassArrow = document.getElementById("compassArrow");
            if (windDirection !== "--" && compassArrow) {
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
                const rotation = directions[windDirection] || 0;
                compassArrow.style.transform = `translate(-50%, -50%) rotate(${rotation}deg)`;
            }

            // Convert timestamp from API into readable format
            const formattedTimestamp = new Date(timestamp).toLocaleString("no-NO", {
                year: "numeric",
                month: "2-digit",
                day: "2-digit",
                hour: "2-digit",
                minute: "2-digit"
            });

            // Update "Last Updated" time with actual database timestamp
            if (lastUpdatedElement) {
                lastUpdatedElement.textContent = `Sist oppdatert: ${formattedTimestamp}`;
            }
        } catch (error) {
            console.error("Error updating weather data:", error);

            // Handle fetch failure by showing an error message
            if (lastUpdatedElement) {
                lastUpdatedElement.textContent = `Kunne ikke oppdatere data. Feil: ${error.message}`;
            }
        }
    };

    // Initial update and refresh every 60 seconds
    updateWeather();
    setInterval(updateWeather, 60000); // Fetch and update every minute
});

