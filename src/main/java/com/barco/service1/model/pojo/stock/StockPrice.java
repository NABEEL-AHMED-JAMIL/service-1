package com.barco.service1.model.pojo.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Nabeel Ahmed
 */
@Entity
@Table(name = "stock_price")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockPrice {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "open_price")
    private Double openPrice;

    @Column(name = "high_price")
    private Double highPrice;

    @Column(name = "low_price")
    private Double lowPrice;

    @Column(name = "close_price")
    private Double closePrice;

    @Column(name = "wap")
    private Double wap;

    @Column(name = "no_shares")
    private Integer noOfShares;

    @Column(name = "no_trades")
    private Integer noOfTrades;

    @Column(name = "total_turnover")
    private Double totalTurnover;

    @Column(name = "deliverable_qty")
    private Integer deliverableQty;

    @Column(name = "deli_qty_traded_qty")
    private Double deliQtyToTradedQty;

    @Column(name = "spread_high_low")
    private Double spreadHighLow;

    @Column(name = "spread_close_open")
    private Double spreadCloseOpen;

    public StockPrice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public Double getWap() {
        return wap;
    }

    public void setWap(Double wap) {
        this.wap = wap;
    }

    public Integer getNoOfShares() {
        return noOfShares;
    }

    public void setNoOfShares(Integer noOfShares) {
        this.noOfShares = noOfShares;
    }

    public Integer getNoOfTrades() {
        return noOfTrades;
    }

    public void setNoOfTrades(Integer noOfTrades) {
        this.noOfTrades = noOfTrades;
    }

    public Double getTotalTurnover() {
        return totalTurnover;
    }

    public void setTotalTurnover(Double totalTurnover) {
        this.totalTurnover = totalTurnover;
    }

    public Integer getDeliverableQty() {
        return deliverableQty;
    }

    public void setDeliverableQty(Integer deliverableQty) {
        this.deliverableQty = deliverableQty;
    }

    public Double getDeliQtyToTradedQty() {
        return deliQtyToTradedQty;
    }

    public void setDeliQtyToTradedQty(Double deliQtyToTradedQty) {
        this.deliQtyToTradedQty = deliQtyToTradedQty;
    }

    public Double getSpreadHighLow() {
        return spreadHighLow;
    }

    public void setSpreadHighLow(Double spreadHighLow) {
        this.spreadHighLow = spreadHighLow;
    }

    public Double getSpreadCloseOpen() {
        return spreadCloseOpen;
    }

    public void setSpreadCloseOpen(Double spreadCloseOpen) {
        this.spreadCloseOpen = spreadCloseOpen;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
