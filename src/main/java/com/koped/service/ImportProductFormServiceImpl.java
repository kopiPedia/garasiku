package com.koped.service;

import java.util.List;
import com.koped.model.ImportForm;
import com.koped.repository.ImportFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ImportProductFormServiceImpl implements ImportProductFormService {

    private final ImportFormRepository importFormRepo;

    @Override
    public ImportForm findByRequestIds(String productId) {
        return importFormRepo.findByRequestId(productId);
    }

    @Override
    public List<ImportForm> findAllRequests() {
        return importFormRepo.findAll();
    }

    @Override
    public String deleteByRequestId(String productId) {
        return importFormRepo.deleteByRequestId(productId);
    }

    @Override
    public ImportForm updateByRequestIds(ImportForm data) {
        return importFormRepo.save(data);
    }

    @Override
    public ImportForm createNewRequests(ImportForm data) {
        return importFormRepo.save(data);
    }

}