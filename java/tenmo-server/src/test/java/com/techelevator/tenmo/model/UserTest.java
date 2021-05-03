package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        User user = new User();

        user.setId(Long.valueOf(1));

        Assert.assertEquals(Long.valueOf(1), user.getId());
    }

    @Test
    void getUsername() {
        User user = new User();

        user.setUsername("bob");

        Assert.assertEquals("bob", user.getUsername());
    }

    @Test
    void getPassword() {
        User user = new User();

        user.setPassword("bobby");

        Assert.assertEquals("bobby", user.getPassword());
    }

    @Test
    void isActivated() {
        User user = new User();

        boolean isActivated;

        isActivated = user.isActivated();


        Assert.assertEquals(isActivated, user.isActivated());
    }

    @Test
    void getAuthorities() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
        User user = new User();
        User user1= new User();

        user.setActivated(user.isActivated());
        user1.setActivated(user1.isActivated());

        Assert.assertTrue(user.equals(user1) && user1.equals(user));
        Assert.assertTrue(user.hashCode() == user1.hashCode());
    }

    @Test
    void testToString() {
        User user = new User();

        Assert.assertTrue(user.toString().contains("id=" + user.getId()));
        Assert.assertTrue(user.toString().contains(", username='" + user.getUsername()));
        Assert.assertTrue(user.toString().contains(", activated=" + user.isActivated()));
        Assert.assertTrue(user.toString().contains(", authorities=" + user.getAuthorities()));
    }
}