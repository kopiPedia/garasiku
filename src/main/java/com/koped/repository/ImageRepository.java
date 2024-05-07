package com.koped.repository;

import com.koped.model.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, UUID> {
    Optional<Image> findByName(String fileName);
    void deleteByName(String fileName);
}