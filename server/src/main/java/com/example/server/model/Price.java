/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import com.example.server.mapper.SafeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Optional;

/**
 *
 * @author luis.renteria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {

    public Price() {
    }

    private String currency;
    private String total;
    private String base;
    @JsonDeserialize(using = SafeDeserializer.class)
    private Fee[] fees;
    private String grandTotal;
    @JsonDeserialize(using = SafeDeserializer.class)
    private Optional<Fee[]> additionalServices;

    public Price(String currency, String total, String base, Fee[] fees, String grandTotal, Optional<Fee[]> additionalServices) {
        this.currency = currency;
        this.total = total;
        this.base = base;
        this.fees = fees;
        this.grandTotal = grandTotal;
        this.additionalServices = additionalServices;
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

    public Fee[] getFees() {
        return fees;
    }

    public void setFees(Fee[] fees) {
        this.fees = fees;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Optional<Fee[]> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Optional<Fee[]> additionalServices) {
        this.additionalServices = additionalServices;
    }

    @Override
    public String toString() {
        return "Price{" + "currency=" + currency + ", total=" + total + ", base=" + base + ", fees=" + fees + ", grandTotal=" + grandTotal + ", additionalServices=" + additionalServices + '}';
    }

}
