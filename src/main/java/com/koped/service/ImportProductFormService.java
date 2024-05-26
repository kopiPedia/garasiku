package com.koped.service;

import com.koped.model.ImportForm;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ImportProductFormService {

    ResponseEntity<ImportForm> findByRequestIds(String requestId);
    List<ImportForm> findAllRequests();
    ResponseEntity<String> deleteByRequestId(String requestId);
    ResponseEntity<ImportForm> updateByRequestIds(String requestId, ImportForm data, MultipartFile image) throws IOException;
    ResponseEntity<ImportForm> createNewRequests(ImportForm data, MultipartFile image) throws IOException;
    List<ImportForm> findAllByUserId(int userId);
}