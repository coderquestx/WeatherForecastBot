package com.weather.service;

import com.weather.api.WeatherApiClient;
import com.weather.dto.WeatherApiResponse;
import com.weather.model.WeatherData;
import com.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherService {
    @Autowired
    private WeatherApiClient weatherApiClient;

    @Autowired
    private WeatherRepository weatherDataRepository;

    @Value("weather.token")
    private String apiKey;

    public WeatherData fetchAndStoreWeather(String city) {
        WeatherApiResponse response = weatherApiClient.getWeather(city, apiKey);
        WeatherData weatherData = new WeatherData();
        weatherData.setCity(response.getName());
        weatherData.setTemperature(response.getMain().getTemp());
        weatherData.setDescription(response.getWeather().get(0).getDescription());
        weatherData.setTimestamp(LocalDateTime.now());
        return weatherDataRepository.save(weatherData);
    }
}
