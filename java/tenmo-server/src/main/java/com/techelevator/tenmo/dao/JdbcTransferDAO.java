package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDAO implements TransferDAO{

    private JdbcTemplate jdbcTemplate;
    public JdbcTransferDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfer> getAllTransfers(Transfer transfer) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount FROM transfers";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Transfer transfer1 = mapRowToTransfer(results);
            transfers.add(transfer1);
        }
        return transfers;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        //Check to make sure user has enough money for transfer
        Account account = new Account();
        User user = new User();



        String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, transfer.getTransferType(), transfer.getTransferStatus(), transfer.getTransferFromAccountId(),
                transfer.getTransferToAccountId(), transfer.getTransferAmount());

        // calculate new balance (both From account & To account)
        // Will have to pass in both new balance & accountId
        // get account from ID
        // get account To ID
        // call update method Twice (one for from, one for to)
        // all should be wrapped in IF statement for enough money for transfer (could do If statement in client-side)

        //updateAccountBalance();

        return transfer;
    }

    @Override
    public Account getAccountByUserId(long id) {
        String sql = "SELECT account_id, user_id, balance FROM accounts WHERE user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        if (rowSet.next()){
            return mapRowToAccount(rowSet);
        }
        return null;
    }

    @Override
    public Account getAccountByAccountId(long id) {
        String sql = "SELECT account_id, user_id, balance FROM accounts WHERE account_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        if (rowSet.next()){
            return mapRowToAccount(rowSet);
        }
        return null;
    }

    public void updateAccountBalance(BigDecimal balance, long accountId){
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

        jdbcTemplate.update(sql, balance, accountId);
    }

    public List<Transfer> findTransfersByUsername(String username) {

        List<Transfer> transfers = new ArrayList<Transfer>();
       // System.out.println("username being passed: " + username);

        String sql = "SELECT t.transfer_id, t.transfer_type_id, t.transfer_status_id, u.username, t.account_from, t.account_to, t.amount FROM transfers t " +
                "JOIN accounts a ON t.account_from = a.account_id OR t.account_to = a.account_id " +
                "JOIN users u ON a.user_id = u.user_id " +
                "WHERE u.username = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        while (rowSet.next()) {
            transfers.add(mapRowToTransfer(rowSet));
        }

        System.out.println();

        return transfers;
    }

   /* public Transfer findUserToByTransferId(long transferId) {
        String sql = "SELECT t.transfer_id, u.username, t.amount FROM transfers t " +
                "JOIN accounts a ON t.account_to = a.account_id " +
                "JOIN users u ON a.user_id = u.user_id " +
                "WHERE t.transfer_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transferId);
        if (rowSet.next()) {
            return mapRowToTransfer(rowSet);
        }
        return null;

    }*/


    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getLong("transfer_id"));
        transfer.setTransferType(rs.getInt("transfer_type_id"));
        transfer.setTransferStatus(rs.getInt("transfer_status_id"));
        transfer.setTransferFromAccountId(rs.getLong("account_from"));
        transfer.setTransferToAccountId(rs.getLong("account_to"));
        transfer.setTransferAmount(rs.getBigDecimal("amount"));
        return transfer;
    }

   /* private Transfer mapRowToUserTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getLong("transfer_id"));
        transfer.setTransferAmount(rs.getBigDecimal("amount"));
        return transfer;
    }*/

    private Account mapRowToAccount (SqlRowSet rs) {
        Account account = new Account();
        account.setAccountId(rs.getLong("account_id"));
        account.setUserId(rs.getLong("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }
}
