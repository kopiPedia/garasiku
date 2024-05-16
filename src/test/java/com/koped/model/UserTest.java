package com.koped.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testPositiveScenarios() {
        User user = new User();
        assertNotNull(user);

        user.setId(1);
        assertEquals(1, user.getId());
        
        user.setUsername("testuser");
        assertEquals("testuser", user.getUsername());
        
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
        
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testNegativeScenarios() {
        User user = new User();
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
    }
    
    @Test
    public void testEmailFormat() {
        // Test if the email format is valid
        User user = new User();
        String validEmail = "test@example.com";
        String invalidEmail = "invalid_email";
        
        user.setEmail(validEmail);
        assertEquals(validEmail, user.getEmail());
        
        // Ensure invalid email is not accepted
        try {
            user.setEmail(invalidEmail);
            fail("Invalid email should not be accepted");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
        }
    }
    
    @Test
    public void testPasswordLength() {
        // Test if password length is at least 8 characters
        User user = new User();
        String validPassword = "password123";
        String invalidPassword = "pass123";
        
        user.setPassword(validPassword);
        assertEquals(validPassword, user.getPassword());
        
        // Ensure invalid password is not accepted
        try {
            user.setPassword(invalidPassword);
            fail("Password length less than 8 characters should not be accepted");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testSetUsernameWithNull() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername(null);
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testSetUsernameWithEmptyString() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("");
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testSetPasswordWithShortPassword() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("pass");
        });
        assertEquals("Password must be at least 8 characters long", exception.getMessage());
    }

    @Test
    public void testSetPasswordWithNull() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword(null);
        });
        assertEquals("Password must be at least 8 characters long", exception.getMessage());
    }

    @Test
    public void testSetEmailWithInvalidEmail() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail("test@.com");
        });
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    public void testSetEmailWithNull() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail(null);
        });
        assertEquals("Invalid email format", exception.getMessage());
    }

}

