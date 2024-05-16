package com.koped.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

public class ImageTest {

    @Test
    public void testNoArgsConstructor() {
        Image image = new Image();
        assertNotNull(image, "Image object should be instantiated.");
    }

    @Test
    public void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String name = "Example Image";
        String type = "jpeg";
        byte[] imageData = new byte[]{1, 2, 3};

        Image image = new Image(id, name, type, imageData);
        assertEquals(id, image.getId(), "ID should match the constructor input");
        assertEquals(name, image.getName(), "Name should match the constructor input");
        assertEquals(type, image.getType(), "Type should match the constructor input");
        assertArrayEquals(imageData, image.getImageData(), "ImageData should match the constructor input");
    }

    @Test
    public void testSetters() {
        Image image = new Image();
        UUID id = UUID.randomUUID();
        image.setId(id);
        assertEquals(id, image.getId(), "Setter for id failed");

        String name = "New Image";
        image.setName(name);
        assertEquals(name, image.getName(), "Setter for name failed");

        String type = "png";
        image.setType(type);
        assertEquals(type, image.getType(), "Setter for type failed");

        byte[] imageData = {0x10, 0x20};
        image.setImageData(imageData);
        assertArrayEquals(imageData, image.getImageData(), "Setter for imageData failed");
    }

}
