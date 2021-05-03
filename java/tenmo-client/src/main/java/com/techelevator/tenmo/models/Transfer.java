package com.techelevator.tenmo.models;

import java.math.BigDecimal;

public class Transfer {
    private long transferId;
    private int transferType;
    private long transferFromAccountId;
    private long transferToAccountId;
    private int transferStatus;
    private BigDecimal transferAmount;

    public long getTransferId() {
        return transferId;
    }

    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }

    public long getTransferFromAccountId() {
        return transferFromAccountId;
    }

    public void setTransferFromAccountId(long transferFromAccountId) {
        this.transferFromAccountId = transferFromAccountId;
    }

    public long getTransferToAccountId() {
        return transferToAccountId;
    }

    public void setTransferToAccountId(long transferToAccountId) {
        this.transferToAccountId = transferToAccountId;
    }

    public int getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(int transferStatus) {
        this.transferStatus = transferStatus;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    @Override
    public String toString() {
        return getTransferId() + " " + getTransferType() + " " + getTransferStatus() + " " + getTransferFromAccountId() +
                " " + getTransferToAccountId() + " " + getTransferAmount();
    }
}
