package com.koped.model;

import com.koped.enums.PaymentMethod;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@Table(name = "tbl_voucher")
@Entity
public class Voucher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String voucherId;

    @Column(name = "voucher_name", unique = true)
    private String voucherName;

    @Column(name = "voucher_quantity")
    private int  voucherQuantity;

    @Column(name = "method")
    private PaymentMethod method;

    public Voucher () {

    }

    public Voucher(String voucherId, String voucherName, int voucherQuantity) {
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.voucherQuantity = voucherQuantity;
        this.method = PaymentMethod.VOUCHER;
    }
}