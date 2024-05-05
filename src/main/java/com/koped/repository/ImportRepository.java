package com.koped.repository;

import com.koped.model.ImportProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ImportRepository extends JpaRepository<ImportProduct, Integer> {

    //Product
    ImportProduct findByProductId(String productId);
    String deleteByProductId(String productId);

    //User
    ImportProduct  findByUserId(int userId);
}
