package com.weather.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class Yeaterday implements Serializable {
    private static final long serialVersionUID = -8901576707514518076L;
    private String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;
}
