package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AuthorityTest {

    @Test
    void getName() {
        Authority authorityName = new Authority("bob");

        authorityName.setName("bob");

        Assert.assertEquals("bob", authorityName.getName());
    }

    @Test
    void testEquals() {
       // Authority authority = new Authority("bob");
       // Object o = new Object();

       // authority.equals(o);

        //Assert.assertEquals(true, authority.equals(o));
    }

    @Test
    void testHashCode() {
        Authority authority = new Authority("bob");
        Authority authority1 = new Authority("bob");

        Assert.assertTrue(authority.equals(authority1) && authority1.equals(authority));
        Assert.assertTrue(authority.hashCode() == authority1.hashCode());

    }

    @Test
    void testToString() {
        Authority authority = new Authority("bob");

        String expected = authority.getName();

        Assert.assertTrue(authority.toString().contains("name=" + authority.getName()));
    }
}