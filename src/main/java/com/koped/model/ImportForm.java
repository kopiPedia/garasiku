package com.koped.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tbl_importform")
public class ImportForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "requestId")
    private String requestId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "expected_country")
    private String expectedCountry;

    @Column(name = "details")
    private String details;

    @Column(name = "budget_range")
    private BigDecimal budgetRange;

    @Column(name = "image")
    private String image;


    @Column(name = "status")
    private String status;

    public void setBudgetRange(BigDecimal budgetRange) {
        if (budgetRange.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Budget range cannot be negative");
        }
        this.budgetRange = budgetRange;
    }

    public void setProductName(String productName) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        this.productName = productName;
    }
}
