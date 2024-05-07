package com.koped.restcontroller;

import com.koped.model.Image;
import com.koped.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/image")
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ImageRestController {
    @Autowired
    private ImageService imageService;
    @PostMapping("")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile image) throws IOException {
        Image uploadImage = imageService.uploadImage(image);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] downloadImage = imageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }
}