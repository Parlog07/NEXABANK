package com.nexabank.model;

import java.util.HashSet;

public class Account {

    private String accountNumber;
    private double balance;
    private AccountType accountType;
    private HashSet<Transaction> transactionHistory;

    public Account(String accountNumber, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = 0.0;
        this.transactionHistory = new HashSet<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public HashSet<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}