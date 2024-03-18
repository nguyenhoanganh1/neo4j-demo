package com.demo.neo4j.entity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class Account {
    private UUID id;

    private String accountNumber;
    private Double balance;

    // Getters
    public UUID getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(Double balance) {
        this.balance = balance;

    }
}
