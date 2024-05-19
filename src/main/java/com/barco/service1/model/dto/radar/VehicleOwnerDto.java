package com.barco.service1.model.dto.radar;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleOwnerDto {

    private Long ownerId;
    private String ownerName;
    private String email;
    private String docNo; // national id car
    private String passport; // optional
    private String phone;
    private String imgPath;
    private String barCode; // user barcode
    private List<VehicleDto> vehicleDtos;

    public VehicleOwnerDto() {
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public List<VehicleDto> getVehicles() {
        return vehicleDtos;
    }

    public void setVehicles(List<VehicleDto> vehicleDtos) {
        this.vehicleDtos = vehicleDtos;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
