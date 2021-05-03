package com.techelevator.tenmo.model;

import io.swagger.models.auth.In;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TransferTest {

    @Test
    public void getTransferId() {
        Transfer transferData = new Transfer();
        transferData.setTransferId(2);
        Assert.assertEquals(2, transferData.getTransferId());
    }

    @Test
    public void setTransferId() {
        Transfer transferSet = new Transfer();
        transferSet.setTransferId(2);
        Assert.assertEquals(2, transferSet.getTransferId());
    }

    @Test
    public void getTransferType() {
        Transfer transferType = new Transfer();
        transferType.setTransferType(1);
        Assert.assertEquals(1, transferType.getTransferType());
    }

    @Test
    public void setTransferType() {
    }

    @Test
    public void getTransferFromAccountId() {
        Transfer transferFromAccount = new Transfer();
        transferFromAccount.setTransferFromAccountId(1);
        Assert.assertEquals(1, transferFromAccount.getTransferFromAccountId());
    }

    @Test
    public void setTransferFromAccountId() {
    }

    @Test
    public void getTransferToAccountId() {
        Transfer transferToAccount = new Transfer();
        transferToAccount.setTransferToAccountId(1);
        Assert.assertEquals(1, transferToAccount.getTransferToAccountId());
    }

    @Test
    public void setTransferToAccountId() {
    }

    @Test
    public void getTransferStatus() {
        Transfer transferStatus = new Transfer();
        transferStatus.setTransferStatus(1);
        Assert.assertEquals(1, transferStatus.getTransferStatus());
    }

    @Test
    public void setTransferStatus() {
    }

    @Test
    public void getTransferAmount() {
        Transfer transferAmount = new Transfer();
        transferAmount.setTransferAmount(BigDecimal.valueOf(1));
        Assert.assertEquals(BigDecimal.valueOf(1), transferAmount.getTransferAmount());
    }

    @Test
    public void setTransferAmount() {
    }
}