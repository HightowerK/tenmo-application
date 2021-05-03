package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcTransferDAO;
import com.techelevator.tenmo.dao.JdbcUserDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.NewTransfer;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TransferController {

    @Autowired
    private UserDAO jdbcUserDAO;

    @Autowired
    private TransferDAO jdbcTransferDAO;

    @RequestMapping(path = "transfer", method = RequestMethod.POST)
    public void sendTransfer(@RequestBody NewTransfer transfer) {

        System.out.println(transfer.getFromUserId());
        System.out.println(transfer.getToUserId());



        // Find Account from who we're taking from
        Account fromAccount = jdbcTransferDAO.getAccountByUserId(transfer.getFromUserId());
        // Find Account to where it's going
        Account toAccount = jdbcTransferDAO.getAccountByUserId(transfer.getToUserId());
        // Make sure Account we're taking from has enough money
        if (fromAccount.getBalance().compareTo(transfer.getAmount()) == 0
                || fromAccount.getBalance().compareTo(transfer.getAmount()) == 1) {
        // Update Account balance from where we're taking
            BigDecimal newBalance = fromAccount.getBalance().subtract(transfer.getAmount());
            fromAccount.setBalance(newBalance);

        // Update Account balance to where it's going
            newBalance = toAccount.getBalance().add(transfer.getAmount());
            toAccount.setBalance(newBalance);

            jdbcTransferDAO.updateAccountBalance(fromAccount.getBalance(), fromAccount.getAccountId());
            jdbcTransferDAO.updateAccountBalance(toAccount.getBalance(), toAccount.getAccountId());
        // Create a transfer
            Transfer transfer1 = new Transfer();
            transfer1.setTransferAmount(transfer.getAmount());
            transfer1.setTransferFromAccountId(fromAccount.getAccountId());
            transfer1.setTransferToAccountId(toAccount.getAccountId());
            transfer1.setTransferStatus(2);
            transfer1.setTransferType(2);
            jdbcTransferDAO.createTransfer(transfer1);
        } else {
            System.out.println("Not enough funds for transfer.");
        }
    }

   /* //Find Account from who we're taking from
    @RequestMapping(path = "account/{id}", method = RequestMethod.GET)
    public Account getAccountFrom(@PathVariable int id) {
        Account account = jdbcTransferDAO.getAccountByUserId(id);
        return account;
    }*/

   /* //Find Account to where it's going
    @RequestMapping(path = "account/{id}", method = RequestMethod.GET)
    public Account getAccountTo(@PathVariable int id) {
        Account account = jdbcTransferDAO.getAccountByUserId(id);
        return account;
    }*/

    //Update Account Balance where we're taking from
 /*   @RequestMapping(path = "account/{id}/balance", method = RequestMethod.PUT)
    public void updateAccountFromBalance(@RequestBody Account account, @PathVariable int id) {
        jdbcTransferDAO.updateAccountBalance(account, id);

    }*/

  /*  @RequestMapping(path = "account/{id}/balance", method = RequestMethod.PUT)
    public void updateAccountToBalance(@RequestBody Account account, @PathVariable int id) {
        jdbcTransferDAO.updateAccountBalance(account, id);
    }*/

  /*  @RequestMapping(path = "transfer", method = RequestMethod.POST)
    public Transfer createTransfer(@RequestBody Transfer transfer) {
        return jdbcTransferDAO.createTransfer(transfer);
    }*/


}
