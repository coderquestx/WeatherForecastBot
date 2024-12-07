@SpringBootTest
public class WeatherServiceTest {

    @Mock
    private WeatherApiClient weatherApiClient;

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService weatherService;

    @Value("${weather.api.key}")
    private String apiKey;

    @Test
    public void fetchAndStoreWeatherTest_Success() {
        String city = "New York";
        WeatherApiResponse apiResponse = new WeatherApiResponse();
        apiResponse.setName(city);
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemp(25.5);
        apiResponse.setMain(main);
        WeatherApiResponse.Weather weather = new WeatherApiResponse.Weather();
        weather.setDescription("Clear sky");
        apiResponse.setWeather(List.of(weather));

        when(weatherApiClient.getWeather(city, apiKey)).thenReturn(apiResponse);

        WeatherData savedData = new WeatherData();
        savedData.setCity(city);
        savedData.setTemperature(25.5);
        savedData.setDescription("Clear sky");
        savedData.setTimestamp(LocalDateTime.now());
        when(weatherRepository.save(any(WeatherData.class))).thenReturn(savedData);

        WeatherData result = weatherService.fetchAndStoreWeather(city);

        assertNotNull(result);
        assertEquals(city, result.getCity());
        assertEquals(25.5, result.getTemperature());
        assertEquals("Clear sky", result.getDescription());

        verify(weatherApiClient, times(1)).getWeather(city, apiKey);
        verify(weatherRepository, times(1)).save(any(WeatherData.class));
    }
}
