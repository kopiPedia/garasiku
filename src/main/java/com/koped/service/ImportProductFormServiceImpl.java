package com.koped.service;

import com.koped.model.ImportForm;
import com.koped.repository.ImportFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class ImportProductFormServiceImpl implements ImportProductFormService {

    private final ImportFormRepository importFormRepo;

    @Override
    public ResponseEntity<ImportForm> findByRequestIds(String requestId) {
        ImportForm form = importFormRepo.findByRequestId(requestId);
        if (form == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(form);
    }

    @Override
    public List<ImportForm> findAllByUserId(int userId) {
        return importFormRepo.findAllByUserId(userId);
    }


    @Override
    public List<ImportForm> findAllRequests() {
        return importFormRepo.findAll();
    }

    @Override
    public ResponseEntity<String> deleteByRequestId(String requestId) {
        if (importFormRepo.findByRequestId(requestId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found");
        }
        importFormRepo.deleteByRequestId(requestId);
        return ResponseEntity.status(HttpStatus.OK).body("Request deleted successfully");
    }

    @Override
    public ResponseEntity<ImportForm> updateByRequestIds(String requestId, ImportForm data) {
        ImportForm existingForm = importFormRepo.findByRequestId(requestId);
        if (existingForm == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Update form data
        existingForm.setProductName(data.getProductName());
        existingForm.setExpectedCountry(data.getExpectedCountry());
        existingForm.setDetails(data.getDetails());
        existingForm.setBudgetRange(data.getBudgetRange());
        existingForm.setImage(data.getImage());

        // Validate and set status
        ResponseEntity<String> validationResponse = validateAndSetStatus(data.getStatus());
        if (validationResponse != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        existingForm.setStatus(data.getStatus());

        ImportForm updatedForm = importFormRepo.save(existingForm);
        return ResponseEntity.status(HttpStatus.OK).body(updatedForm);
    }


    @Override
    public ResponseEntity<ImportForm> createNewRequests(ImportForm data) {
        // Generate unique requestId
        String requestId = generateUniqueRequestId();
        data.setRequestId(requestId);

        // Validate and set status
        ResponseEntity<String> validationResponse = validateAndSetStatus(data.getStatus());
        if (validationResponse != null) {
            // Return the validation response if the status is invalid
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Validate the fields before saving
        ResponseEntity<String> fieldValidationResponse = validateImportForm(data);
        if (fieldValidationResponse != null) {
            // Return the field validation response if there are errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        ImportForm savedForm = importFormRepo.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedForm);
    }


    // Method to validate and set status
    private ResponseEntity<String> validateAndSetStatus(String status) {
        List<String> validStatusList = List.of("PLACED", "CONFIRMED", "PROCESSED", "SHIPPING", "DELIVERED", "FAILED");
        if (!validStatusList.contains(status)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status: " + status);
        }
        return null;
    }

    // Method to validate the ImportForm fields
    private ResponseEntity<String> validateImportForm(ImportForm form) {
        if (form == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Form data cannot be null");
        }

        return null;
    }

    // Method to generate unique requestId
    private String generateUniqueRequestId() {
        Random random = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        StringBuilder requestId = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            requestId.append(letters.charAt(random.nextInt(letters.length())));
        }
        for (int i = 0; i < 3; i++) {
            requestId.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        // Check if requestId already exists, generate a new one if needed
        while (importFormRepo.findByRequestId(requestId.toString()) != null) {
            for (int i = 0; i < 3; i++) {
                requestId.setCharAt(i, letters.charAt(random.nextInt(letters.length())));
            }
            for (int i = 3; i < 6; i++) {
                requestId.setCharAt(i, numbers.charAt(random.nextInt(numbers.length())));
            }
        }

        return requestId.toString();
    }
}
