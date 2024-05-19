package com.barco.service1.model.pojo.radar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Route {

    private Long id;
    private String routeName;
    private String routeLine; // left/right

    public Route() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteLine() {
        return routeLine;
    }

    public void setRouteLine(String routeLine) {
        this.routeLine = routeLine;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
