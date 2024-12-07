package com.weather.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.weather.dto.WeatherApiResponse;

@FeignClient(name = "weatherApi", url = "https://api.openweathermap.org/data/2.5")
public interface WeatherApiClient {
    @GetMapping("/weather")
    WeatherApiResponse getWeather(@RequestParam("q") String city, @RequestParam("appid") String apiKey);
}

