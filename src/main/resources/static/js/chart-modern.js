document.addEventListener("DOMContentLoaded", () => {
    const apiUrl = "/api/weather/historical";

    const ctx = document.getElementById('weatherChart').getContext('2d');
    const weatherChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [], // Time labels will be populated dynamically
            datasets: [
                {
                    label: 'Tempratur (°C)',
                    data: [],
                    borderColor: '#ff4500',
                    borderWidth: 3,
                    tension: 0.3,
                    fill: false,
                    pointRadius: 0, // Completely removes the dots
                    pointHoverRadius: 0, // Ensures no dots appear on hover
                },
                {
                    label: 'Luftfuktighet (%)',
                    data: [],
                    borderColor: '#1e90ff',
                    borderWidth: 3,
                    tension: 0.3,
                    fill: false,
                    pointRadius: 0, // Completely removes the dots
                    pointHoverRadius: 0, // Ensures no dots appear on hover
                },
                {
                    label: 'Lufttrykk (hPa)',
                    data: [],
                    borderColor: '#32cd32',
                    borderWidth: 3,
                    tension: 0.3,
                    fill: false,
                    pointRadius: 0, // Completely removes the dots
                    pointHoverRadius: 0, // Ensures no dots appear on hover
                }
            ]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: true,
                    position: 'top',
                    labels: {
                        font: {
                            size: 14,
                            weight: 'bold'
                        },
                        color: '#333',
                        usePointStyle: true // Use point style for better aesthetics
                    }
                }
            },
            scales: {
                x: {
                    title: {
                        display: true,
                        text: 'Tid',
                        color: '#555',
                        font: {
                            size: 16,
                            weight: 'bold'
                        }
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: 'Målinger',
                        color: '#555',
                        font: {
                            size: 16,
                            weight: 'bold'
                        }
                    }
                }
            }
        }
    });

    const updateChart = async (timeframe, dataType) => {
        try {
            console.log(`Fetching data with timeframe: ${timeframe}, type: ${dataType}`);

            const response = await fetch(`${apiUrl}?timeframe=${timeframe}&type=${dataType}`);
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

            const data = await response.json();
            console.log("API Response:", data);

            const labels = data.map(entry => entry.timestamp); // Correct key
            const values = data.map(entry => entry.value);

            console.log("Labels:", labels);
            console.log("Values:", values);

            // Update the chart
            weatherChart.data.labels = labels;
            weatherChart.data.datasets.forEach((dataset, index) => {
                if (index === ['temperature', 'humidity', 'pressure'].indexOf(dataType)) {
                    dataset.data = values;
                    dataset.hidden = false; // Show this dataset
                } else {
                    dataset.hidden = true; // Hide others
                }
            });

            weatherChart.update();
        } catch (error) {
            console.error("Error updating weather chart data:", error);
        }
    };

    document.getElementById('timeframe').addEventListener('change', (e) => {
        const timeframe = e.target.value;
        const dataType = document.getElementById('dataType').value;
        updateChart(timeframe, dataType);
    });

    document.getElementById('dataType').addEventListener('change', (e) => {
        const dataType = e.target.value;
        const timeframe = document.getElementById('timeframe').value;
        updateChart(timeframe, dataType);
    });

    // Initial load
    updateChart('week', 'temperature');
});

