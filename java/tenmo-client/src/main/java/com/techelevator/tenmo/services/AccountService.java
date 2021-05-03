package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.NewTransfer;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {


    private static String API_BASE_URL = "http://localhost:8080/";
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser authenticatedUser;

    public AccountService(String url, AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        API_BASE_URL = url;
    }

    public BigDecimal getAccountBalance() {
        BigDecimal balance;

            balance = restTemplate.exchange(API_BASE_URL + "balance", HttpMethod.GET, makeAuthEntity(), BigDecimal.class).getBody();

        return balance;
    }

    public User[] getAllUsers() {
        ResponseEntity<User[]> response = restTemplate.exchange(API_BASE_URL + "getallusers", HttpMethod.GET, makeAuthEntity(),
                User[].class);
        return response.getBody();
    }

    public Transfer[] getAllTransfers() {
        ResponseEntity<Transfer[]> response = restTemplate.exchange(API_BASE_URL + "viewpasttransfers", HttpMethod.GET,
                makeAuthEntity(), Transfer[].class);
        return response.getBody();
    }

    public void transfer(int fromUserId, int toUserId, BigDecimal amount) {
        NewTransfer newTransfer = new NewTransfer();
        newTransfer.setFromUserId(fromUserId);
        newTransfer.setToUserId(toUserId);
        newTransfer.setAmount(amount);
        restTemplate.exchange(API_BASE_URL + "transfer", HttpMethod.POST, makeNewTransferAuthEntity(newTransfer), String.class);
    }

    public String getNamefromAccount(long accountId){
        ResponseEntity<String> response = restTemplate.exchange(API_BASE_URL + "getUserNameFromAccountId/" + accountId, HttpMethod.GET,
                makeAuthEntity(), String.class);
        return response.getBody();
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authenticatedUser.getToken());
        HttpEntity entity = new HttpEntity(headers);
        return entity;
    }

    private HttpEntity<NewTransfer> makeNewTransferAuthEntity(NewTransfer newTransfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authenticatedUser.getToken());
        HttpEntity<NewTransfer> entity = new HttpEntity<>(newTransfer, headers);
        return entity;
    }


}
