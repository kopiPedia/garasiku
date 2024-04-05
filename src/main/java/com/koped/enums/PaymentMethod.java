package com.koped.enums;
import lombok.Getter;

@Getter
public enum PaymentMethod {
    VOUCHER("voucher");
    private final String value;

    private PaymentMethod(String value) {
        this.value = value;
    }
}