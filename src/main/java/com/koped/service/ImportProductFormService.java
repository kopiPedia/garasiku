package com.koped.service;

import com.koped.model.ImportForm;
import com.koped.model.ImportProduct;

import java.util.List;

public interface ImportProductFormService {

    ImportForm findByRequestIds(String productId);
    List<ImportForm> findAllRequests();
    String deleteByRequestId(String productId);
    ImportForm updateByRequestIds(ImportForm data);
    ImportForm createNewRequests(ImportForm data);


}
