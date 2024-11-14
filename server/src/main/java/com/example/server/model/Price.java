/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import java.util.List;

/**
 *
 * @author luis.renteria
 */
public class Price {

    private String currency;
    private String total;
    private String base;
    private String grandTotal;

    private List<Fee> fees;

    public Price(String currency, String total, String base, String grandTotal, List<Fee> fees) {
        this.currency = currency;
        this.total = total;
        this.base = base;
        this.grandTotal = grandTotal;
        this.fees = fees;
    }

    public String getCurrency() {
        return currency;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
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

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

}
