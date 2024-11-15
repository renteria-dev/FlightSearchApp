/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author luis.renteria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelerPricingPrice {

    public TravelerPricingPrice() {
    }

    private String currency;
    private String total;
    private String base;

    public TravelerPricingPrice(String currency, String total, String base) {
        this.currency = currency;
        this.total = total;
        this.base = base;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "TravelerPricingPrice{" + "currency=" + currency + ", total=" + total + ", base=" + base + '}';
    }

}
