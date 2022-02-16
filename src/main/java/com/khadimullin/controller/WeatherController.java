package com.khadimullin.controller;

public class WeatherController {
    private ForecastService forecastService;
    private JsonHelper jsonHelper;

    @PostMapping("/weather")
    public ForecastDTO createWeather(@Valid @RequestBody ForecastFormDTO form){
        Map<String, String> json;
        try {
            json = jsonHelper.parseJson(WeatherHelper.getWeather(form.getCity()));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        Forecast forecast = Forecast.builder()
                .email(form.getEmail())
                .temp(json.get("temp"))
                .description(json.get("description"))
                .city(json.get("name"))
                .build();

        Optional<ForecastDTO> forecastDTO = forecastService.saveForecast(forecast);

        return forecastDTO.orElse(null);
    }

    @GetMapping("/weather")
    public List<ForecastDTO> getWeather(){
        return forecastService.getForecasts();
    }
}
}
