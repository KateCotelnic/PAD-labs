package com.example.tripService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class PaymentDTO {
    @JsonProperty
    private String userId;
    @JsonProperty
    private String paymentType;

    public String getUserId() {
        return userId;
    }

    public String getPaymentType() {
        return paymentType;
    }
}
