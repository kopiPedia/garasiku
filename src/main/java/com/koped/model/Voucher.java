package com.koped.model;

import com.koped.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter

public class Voucher {
    private String voucherId;
    private String voucherName;
    private int  voucherQuantity;
    private PaymentMethod method = PaymentMethod.VOUCHER;
}