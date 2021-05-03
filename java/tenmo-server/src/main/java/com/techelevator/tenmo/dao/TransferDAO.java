package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDAO {

    List<Transfer> getAllTransfers(Transfer transfer);

    Account getAccountByUserId (long id);

    Account getAccountByAccountId(long id);

    Transfer createTransfer(Transfer transfer);

    void updateAccountBalance(BigDecimal balance, long accountId);



}
