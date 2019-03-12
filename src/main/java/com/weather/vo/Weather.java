package com.weather.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class Weather implements Serializable {
    private static final long serialVersionUID = 4971572550753561708L;
    private String city;
    private String aqi;
    private String ganmao;
    private String wendu;
    private Yeaterday yesterday;
    private List<Forecast> forecast;

}
