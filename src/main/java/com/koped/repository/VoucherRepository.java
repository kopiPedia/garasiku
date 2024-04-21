package com.koped.repository;

import com.koped.model.Voucher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;
import java.util.UUID;

@Repository
public class VoucherRepository {
    private List<Voucher> voucherData = new ArrayList<>();

    public Voucher create(Voucher voucher) {
        return voucher;
    }

    public Iterator<Voucher> findAll() { return voucherData.iterator();}

    public Voucher findById(String id){
        return null;
    }

    public Voucher update(String id, Voucher updatedVoucher){
        return null;
    }

    public void delete(String id){

    }
}