package com.barco.service1.model.dto.radar;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class VehicleRadarDto {

    private Long id;
    private RadarDto radarDto;
    private RouteDto routeDto;
    private VehicleDto vehicleDto;
    private VehicleOwnerDto vehicleOwnerDto;
    private boolean violation;
    private Long recordSpeed;
    private Timestamp recordTime;
    private String imgPath;

    public VehicleRadarDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RadarDto getRadar() {
        return radarDto;
    }

    public void setRadar(RadarDto radarDto) {
        this.radarDto = radarDto;
    }

    public RouteDto getRoute() {
        return routeDto;
    }

    public void setRoute(RouteDto routeDto) {
        this.routeDto = routeDto;
    }

    public VehicleDto getVehicle() {
        return vehicleDto;
    }

    public void setVehicle(VehicleDto vehicleDto) {
        this.vehicleDto = vehicleDto;
    }

    public VehicleOwnerDto getVehicleOwner() {
        return vehicleOwnerDto;
    }

    public void setVehicleOwner(VehicleOwnerDto vehicleOwnerDto) {
        this.vehicleOwnerDto = vehicleOwnerDto;
    }

    public boolean isViolation() {
        return violation;
    }

    public void setViolation(boolean violation) {
        this.violation = violation;
    }

    public Long getRecordSpeed() {
        return recordSpeed;
    }

    public void setRecordSpeed(Long recordSpeed) {
        this.recordSpeed = recordSpeed;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
