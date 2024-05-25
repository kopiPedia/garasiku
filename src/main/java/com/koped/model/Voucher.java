package com.koped.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Table(name = "tbl_voucher")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Voucher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String voucherId;

    @Column(name = "voucher_name", unique = true)
    private String voucherName;

    @Column(name = "voucher_quantity")
    private int  voucherQuantity;

    @Column(name= "discount")
    private int discount;
}