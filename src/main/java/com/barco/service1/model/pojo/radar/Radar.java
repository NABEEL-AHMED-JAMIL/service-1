package com.barco.service1.model.pojo.radar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Radar {

    private Long id;
    private String radarName;
    private String radarType; // speed|speed+cam
    private String model;
    private Long speedLimit;

    public Radar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRadarName() {
        return radarName;
    }

    public void setRadarName(String radarName) {
        this.radarName = radarName;
    }

    public String getRadarType() {
        return radarType;
    }

    public void setRadarType(String radarType) {
        this.radarType = radarType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(Long speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
