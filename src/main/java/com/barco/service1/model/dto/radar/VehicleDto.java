package com.barco.service1.model.dto.radar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDto {

    private Long id;
    private String registerNo;
    private String vehicleNo;
    private VehicleTypeDto vehicleTypeDto;

    public VehicleDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public VehicleTypeDto getVehicleType() {
        return vehicleTypeDto;
    }

    public void setVehicleType(VehicleTypeDto vehicleTypeDto) {
        this.vehicleTypeDto = vehicleTypeDto;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
