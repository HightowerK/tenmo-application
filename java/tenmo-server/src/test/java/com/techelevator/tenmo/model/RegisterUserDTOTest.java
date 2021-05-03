package com.techelevator.tenmo.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserDTOTest {

    @Test
    void getUsername() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();

        registerUserDTO.setUsername("bob");

        Assert.assertEquals("bob", registerUserDTO.getUsername());
    }

    @Test
    void getPassword() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();

        registerUserDTO.setPassword("bobby");

        Assert.assertEquals("bobby", registerUserDTO.getPassword());
    }
}