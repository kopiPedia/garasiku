package com.koped.service;

import com.koped.model.Image;
import com.koped.repository.ImageRepository;
import com.koped.utilities.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        Date date = new Date();
        Image image = new Image();
        image.setName(date.getTime() + file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImageData(ImageUtil.compressImage(file.getBytes()));
        imageRepository.save(image);

        if (image.getImageData() != null) {
            return image;
        }
        return null;
    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = imageRepository.findByName(fileName);
        return ImageUtil.decompressImage(dbImageData.get().getImageData());
    }

    @Override
    public void deleteImage(String fileName) {
        imageRepository.deleteByName(fileName);
    }
}