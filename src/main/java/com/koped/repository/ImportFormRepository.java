package com.koped.repository;

import com.koped.model.ImportForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportFormRepository extends JpaRepository<ImportForm, Integer> {


    ImportForm  findByRequestId(String requestId);
    String deleteByRequestId(String requestId);
    List<ImportForm> findAllByUserId(int userId);
}
