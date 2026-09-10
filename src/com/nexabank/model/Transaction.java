package com.nexabank.model;

import java.time.LocalDateTime;

public class Transaction {

    private String idTransaction;
    private TransactionType type;
    private double amount;
    private LocalDateTime date;
    private Account sourceAccount;
    private Account destinationAccount;

    public Transaction(
            String idTransaction,
            TransactionType type,
            double amount,
            Account sourceAccount,
            Account destinationAccount
    ) {
        this.idTransaction = idTransaction;
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }
}