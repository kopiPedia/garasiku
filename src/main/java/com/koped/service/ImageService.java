package com.koped.service;

import com.koped.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public Image uploadImage(MultipartFile file) throws IOException;
    public byte[] downloadImage(String fileName);
    public void deleteImage(String fileName);

}