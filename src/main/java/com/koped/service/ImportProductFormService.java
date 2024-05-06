package com.koped.service;

import com.koped.model.ImportForm;

import java.util.List;

public interface ImportProductFormService {

    ImportForm findByRequestIds(String requestId);
    List<ImportForm> findAllRequests();
    String deleteByRequestId(String requestId);
    ImportForm updateByRequestIds(ImportForm data);
    ImportForm createNewRequests(ImportForm data);


}
