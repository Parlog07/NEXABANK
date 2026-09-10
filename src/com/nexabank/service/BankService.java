package com.nexabank.service;

import com.nexabank.exception.InsufficientBalanceException;
import com.nexabank.exception.InvalidAmountException;
import com.nexabank.model.Account;
import com.nexabank.model.Transaction;
import com.nexabank.model.TransactionType;
import com.nexabank.utils.StatementFileManager;
import java.io.IOException;

import java.util.UUID;

public class BankService {
    private StatementFileManager statementFileManager = new StatementFileManager();

    public void deposit(Account account, double amount) throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }

        account.setBalance(account.getBalance() + amount);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                TransactionType.DEPOSIT,
                amount,
                null,
                account
        );

        account.addTransaction(transaction);
        statementFileManager.saveTransaction(account, transaction);
    }

    public void withdraw(Account account, double amount)
            throws InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }

        if (amount > account.getBalance()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                TransactionType.WITHDRAWAL,
                amount,
                account,
                null
        );

        account.addTransaction(transaction);
        statementFileManager.saveTransaction(account, transaction);
    }

    public void transfer(Account sourceAccount, Account destinationAccount, double amount)
            throws InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }

        if (amount > sourceAccount.getBalance()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                TransactionType.TRANSFER,
                amount,
                sourceAccount,
                destinationAccount
        );

        sourceAccount.addTransaction(transaction);
        destinationAccount.addTransaction(transaction);
    }
}