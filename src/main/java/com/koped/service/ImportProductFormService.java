package com.koped.service;

import com.koped.model.ImportForm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ImportProductFormService {

    ResponseEntity<ImportForm> findByRequestIds(String requestId);
    List<ImportForm> findAllRequests();
    ResponseEntity<String> deleteByRequestId(String requestId);
    ResponseEntity<ImportForm> updateByRequestIds(String requestId, ImportForm data);
    ResponseEntity<ImportForm> createNewRequests(ImportForm data);
    List<ImportForm> findAllByUserId(int userId);
}