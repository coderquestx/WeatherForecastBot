# Weather Forecast Bot

This project is a Spring Boot-based weather forecasting application integrated with Telegram. It provides users with real-time weather information for any city, leveraging the OpenWeather API. Users can interact with the bot to get weather data and receive notifications via Telegram.

---

## Features

- **Weather Data Retrieval**: Fetch real-time weather data from the OpenWeather API.
- **Telegram Integration**: Get weather information directly via Telegram bot.
- **Database Storage**: Weather data is stored in a PostgreSQL database for future reference.
- **Bot Commands**:
    - `/start`: Start the bot interaction.
    - `/help`: Get a help message explaining how to use the bot.

---

## Technologies

- **Spring Boot**: The main framework used for building the application.
- **Telegram Bot API**: For sending and receiving messages through Telegram.
- **OpenWeather API**: For fetching real-time weather data.
- **PostgreSQL**: The database for storing weather information.
- **JUnit & Mockito**: For unit and integration testing.

---

## Setup

### Prerequisites

1. **Java 17 or later**: Ensure you have JDK 17+ installed.
2. **Maven**: To build and run the application.
3. **PostgreSQL**: You need a running PostgreSQL instance for storing weather data.
4. **Telegram Bot Token**: Create a Telegram bot using BotFather and get your bot's token.

### Configuration

1. **Create a `.env` file** at the root of the project with the following content:

    ```env
    WEATHER_API_KEY=your_openweathermap_api_key
    TELEGRAM_BOT_TOKEN=your_telegram_bot_token
    TELEGRAM_BOT_USERNAME=your_telegram_bot_username
    ```

    - Replace `your_openweathermap_api_key` with your OpenWeather API key.
    - Replace `your_telegram_bot_token` with the token you received from BotFather.
    - Replace `your_telegram_bot_username` with your Telegram bot's username.

2. **Configure PostgreSQL**:
    - Make sure PostgreSQL is running on your machine or on a remote server.
    - Update the `.env` file with your PostgreSQL database details:

---

## Running the Application

To run the application locally, follow these steps:

1. **Build the project**:

    ```bash
    mvn clean install
    ```

2. **Run the application**:

    ```bash
    mvn spring-boot:run
    ```

   This will start the Spring Boot application and your Telegram bot should now be online and responsive.

---

## Using the Telegram Bot

1. Open Telegram and search for your bot by its username.
2. Start a conversation by typing `/start`.
3. Use the `/help` command to get instructions on how to use the bot.
4. Send the name of a city to get the weather information for that location.

---

## API Endpoints

If you prefer to interact with the weather data via an HTTP API instead of the Telegram bot, the following endpoints are available:

- **`GET /weather/{city}`**: Fetch current weather data for a specified city.
    - **Response**:
      ```json
      {
        "city": "New York",
        "temperature": 25.5,
        "description": "Clear sky",
        "timestamp": "2024-12-07T15:30:00"
      }
      ```

---
