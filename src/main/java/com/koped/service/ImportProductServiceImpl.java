package com.koped.service;

import java.util.List;
import com.koped.model.ImportProduct;

import com.koped.repository.ImportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ImportProductServiceImpl implements ImportProductService {

    private final ImportRepository importRepo;

    @Override
    public ImportProduct findByProductIds(String productId) {
        return importRepo.findByProductId(productId);
    }

    @Override
    public List<ImportProduct> findAllProducts() {
        return importRepo.findAll();
    }

    @Override
    public String deleteByProductId(String productId) {
        return importRepo.deleteByProductId(productId);
    }

    @Override
    public ImportProduct updateByProductIds(ImportProduct data) {
        return importRepo.save(data);
    }

    @Override
    public ImportProduct createNewProduct(ImportProduct data) {


        return importRepo.save(data);
    }

    @Override
    public ImportProduct findByUserId(int userId) {
        return importRepo.findByUserId(userId);
    }

}

