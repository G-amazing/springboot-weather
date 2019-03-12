package com.weather.service;

import com.weather.vo.City;

import java.util.List;

public interface CityDataService {
    List<City> listCity() throws Exception;
}
