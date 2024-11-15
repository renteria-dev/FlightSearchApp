/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author luis.renteria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fee {

    public Fee() {
    }
    private String amount;

    @JsonProperty("type")
    private String feeType;

    public Fee(String amount, String feeType) {
        this.amount = amount;
        this.feeType = feeType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @Override
    public String toString() {
        return "Fee{" + "amount=" + amount + ", feeType=" + feeType + '}';
    }

}
