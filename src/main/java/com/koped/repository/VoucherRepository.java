package com.koped.repository;

import com.koped.model.Voucher;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer>{
    Voucher findByVoucherId(String voucherId);
}