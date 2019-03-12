package com.weather.service.impl;

import com.weather.service.WeatherDataService;
import com.weather.service.WeatherReportService;
import com.weather.vo.Weather;
import com.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * 根据城市ID查询天气信息
     */
    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
        return resp.getData();
    }
}
