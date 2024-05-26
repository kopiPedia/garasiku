package com.koped.service;

import com.koped.model.ImportForm;
import com.koped.repository.ImportFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ImportProductFormServiceImpl implements ImportProductFormService {

    private final ImportFormRepository importFormRepository;

    @Override
    public ResponseEntity<ImportForm> findByRequestIds(String requestId) {
        ImportForm importForm = importFormRepository.findByRequestId(requestId);
        if (importForm == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(importForm);
    }

    @Override
    public List<ImportForm> findAllRequests() {
        return importFormRepository.findAll();
    }

    @Override
    public ResponseEntity<String> deleteByRequestId(String requestId) {
        ImportForm importForm = importFormRepository.findByRequestId(requestId);
        if (importForm == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Import form not found");
        }
        importFormRepository.delete(importForm);
        return ResponseEntity.status(HttpStatus.OK).body("Import form deleted successfully");
    }

    @Override
    public ResponseEntity<ImportForm> updateByRequestIds(String requestId, ImportForm updatedImportForm) {
        ImportForm existingImportForm = importFormRepository.findByRequestId(requestId);
        if (existingImportForm == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        existingImportForm.setProductName(updatedImportForm.getProductName());
        existingImportForm.setExpectedCountry(updatedImportForm.getExpectedCountry());
        existingImportForm.setDetails(updatedImportForm.getDetails());
        existingImportForm.setBudgetRange(updatedImportForm.getBudgetRange());
        existingImportForm.setStatus(updatedImportForm.getStatus());

        importFormRepository.save(existingImportForm);
        return ResponseEntity.status(HttpStatus.OK).body(existingImportForm);
    }

    @Override
    public ResponseEntity<ImportForm> createNewRequests(ImportForm data) {
        ImportForm savedForm = importFormRepository.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedForm);
    }

    @Override
    public ResponseEntity<ImportForm> createNewRequests(ImportForm data, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String fileName = String.valueOf(data.getRequestId()) + StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            String uploadDir = "src/main/resources/static/importimages/";
            String uploadPath = uploadDir + fileName;
            Path uploadAbsolutePath = Paths.get(uploadPath);
            Files.createDirectories(uploadAbsolutePath.getParent());
            Files.copy(image.getInputStream(), uploadAbsolutePath);

            String fileNameDB = "../importimages/" + fileName;

            data.setImage(fileNameDB);


        }
        ImportForm savedForm = importFormRepository.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedForm);
    }

    @Override
    public List<ImportForm> findAllByUserId(int userId) {
        return importFormRepository.findAllByUserId(userId);
    }
}
