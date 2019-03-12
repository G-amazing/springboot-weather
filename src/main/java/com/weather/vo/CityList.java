package com.weather.vo;

import lombok.Data;
import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {
    @XmlElement(name = "d")
    private List<City> cityList;
}
