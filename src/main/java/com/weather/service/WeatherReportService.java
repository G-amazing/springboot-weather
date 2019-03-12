package com.weather.service;

import com.weather.vo.Weather;

public interface WeatherReportService {
    Weather getDataByCityId(String cityId);
}
