package com.weather.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.service.WeatherDataService;
import com.weather.vo.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final long TIME_OUT = 1800L; //redis超时时间为30分钟
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    @Override
    public WeatherResponse getDataByCityId(final String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(final String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    private WeatherResponse doGetWeather(String uri) {

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;
        String strBody = null;

        // 先从缓存中查询数据
        if (redisTemplate.hasKey(uri)) {

            strBody = redisTemplate.opsForValue().get(uri);

        } else {
            // 缓存中没有就调用接口查询天气数据
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }

            // 把查询出来的数据写入缓存中
            redisTemplate.opsForValue().set(uri,strBody,TIME_OUT,TimeUnit.SECONDS);
        }

        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            //e.printStackTrace();
            log.error("Error!",e);
        }
        return resp;
    }

    /**
     * 根据城市id来同步天气数据
     */
    @Override
    public void syncDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherDate(uri);
    }

    /**
     * 把天气数据放入缓存中
     */
    private void saveWeatherDate(String uri) {

        String strBody = null;

        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }

        // 把查询出来的数据写入缓存中
        redisTemplate.opsForValue().set(uri,strBody,TIME_OUT,TimeUnit.SECONDS);

    }
}
