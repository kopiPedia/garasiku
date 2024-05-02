package com.koped.service;

import com.koped.model.ImportProduct;
import com.koped.model.Product;

import java.util.List;

public interface ImportProductService {

    ImportProduct findByProductIds(String productId);
    List<ImportProduct> findAllProducts();
    String deleteByProductId(String productId);
    ImportProduct updateByProductIds(ImportProduct data);
    ImportProduct createNewProduct(ImportProduct data);


}
