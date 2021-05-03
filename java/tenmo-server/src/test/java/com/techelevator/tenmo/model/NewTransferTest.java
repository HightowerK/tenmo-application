package com.techelevator.tenmo.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class NewTransferTest {

    @Test
    void getToUserName() {
        NewTransfer newTransfer = new NewTransfer();

        newTransfer.setToUserName("bob");

        Assert.assertEquals("bob", newTransfer.getToUserName());
    }

    @Test
    void getFromUserName() {
        NewTransfer newTransfer = new NewTransfer();

        newTransfer.setFromUserName("bill");

        Assert.assertEquals("bill", newTransfer.getFromUserName());
    }

    @Test
    void getFromUserId() {
        NewTransfer newTransfer = new NewTransfer();

        newTransfer.setFromUserId(1);

        Assert.assertEquals(1, newTransfer.getFromUserId());
    }

    @Test
    void getToUserId() {
        NewTransfer newTransfer = new NewTransfer();

        newTransfer.setToUserId(2);

        Assert.assertEquals(2, newTransfer.getToUserId());
    }

    @Test
    void getAmount() {
        NewTransfer newTransfer = new NewTransfer();

        newTransfer.setAmount(BigDecimal.valueOf(1));

        Assert.assertEquals(BigDecimal.valueOf(1), newTransfer.getAmount());
    }
}