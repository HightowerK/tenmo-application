package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcTransferDAO;
import com.techelevator.tenmo.dao.JdbcUserDAO;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {

    @Autowired
    private JdbcUserDAO jdbcUserDAO;

    @Autowired
    private JdbcTransferDAO jdbcTransferDAO;

    @RequestMapping(path = "balance", method = RequestMethod.GET )
    public BigDecimal balance(Principal principal){
        return jdbcUserDAO.findBalance(jdbcUserDAO.findIdByUsername(principal.getName()));
    }

    @RequestMapping(path = "getallusers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return jdbcUserDAO.findAll();
    }

    @RequestMapping(path = "viewpasttransfers", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers(Principal principal) {
        return jdbcTransferDAO.findTransfersByUsername(principal.getName());
    }

    @RequestMapping(path = "getUserNameFromAccountId/{id}", method = RequestMethod.GET)
    public String returnUserNameFromAccountID(@PathVariable long id){
        return jdbcUserDAO.findUserNameByAccountId(id);

    }

}
