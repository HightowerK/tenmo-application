package com.techelevator.tenmo.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDTOTest {

    @Test
    void getUsername() {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUsername("bob");

        Assert.assertEquals("bob", loginDTO.getUsername());
    }

    @Test
    void getPassword() {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setPassword("bobby");

        Assert.assertEquals("bobby", loginDTO.getPassword());
    }

    @Test
    void testToString() {
        LoginDTO loginDTO = new LoginDTO();

        Assert.assertTrue(loginDTO.toString().contains("username='" + loginDTO.getUsername()));
        Assert.assertTrue(loginDTO.toString().contains(", password='" + loginDTO.getPassword()));
    }
}