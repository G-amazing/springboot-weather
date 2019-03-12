package com.weather.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = 8764853426922603323L;
    private Weather data;
    private Integer status;
    private String desc;
}
