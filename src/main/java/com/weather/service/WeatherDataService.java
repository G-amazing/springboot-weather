package com.weather.service;

import com.weather.vo.WeatherResponse;

public interface WeatherDataService {
    WeatherResponse getDataByCityId(String cityId);

    WeatherResponse getDataByCityName(String cityName);

    void syncDataByCityId(String cityId);
}
