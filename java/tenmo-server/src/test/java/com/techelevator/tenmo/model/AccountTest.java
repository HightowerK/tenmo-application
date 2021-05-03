package com.techelevator.tenmo.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void getAccountId() {
        Account accountId = new Account();
        accountId.setAccountId(1);
        Assert.assertEquals(1, accountId.getAccountId());
    }

    @Test
    public void getUserId() {
        Account userId = new Account();
        userId.setUserId(2);
        Assert.assertEquals(2, userId.getUserId());
    }

    @Test
    public void getBalance() {
        Account balanceYou = new Account();
        balanceYou.setBalance(BigDecimal.valueOf(1));
        Assert.assertEquals(BigDecimal.valueOf(1), balanceYou.getBalance());
    }
}