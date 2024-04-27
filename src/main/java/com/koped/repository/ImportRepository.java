package com.koped.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koped.model.ImportProduct;
@Repository
public interface ImportRepository extends JpaRepository<ImportProduct, Integer> {


    ImportProduct findByProductId(String productId);
    String deleteByProductId(String productId);
    ImportProduct  findByUserId(String userId);
    ImportProduct deleteByUserId(String userId);
    ImportProduct  findByRequestId(String requestId);
    ImportProduct deleteByRequestId(String requestId);
}
