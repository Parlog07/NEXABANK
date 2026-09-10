package com.nexabank.service;

import com.nexabank.exception.AccountNotFoundException;
import com.nexabank.model.Account;
import com.nexabank.model.AccountType;
import com.nexabank.model.Client;

public class AccountService {

    public Account createAccount(
            Client client,
            String accountNumber,
            AccountType accountType
    ) {

        Account account = new Account(accountNumber, accountType);

        client.addAccount(account);

        return account;
    }


    public Account findAccount(Client client, String accountNumber)
            throws AccountNotFoundException {

        Account account = client.findAccount(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account " + accountNumber + " not found"
            );
        }

        return account;
    }


    public void modifyAccountType(
            Client client,
            String accountNumber,
            AccountType newType
    ) throws AccountNotFoundException {

        Account account = findAccount(client, accountNumber);

        account.setAccountType(newType);
    }


    public void closeAccount(
            Client client,
            String accountNumber
    ) throws AccountNotFoundException {

        findAccount(client, accountNumber);

        client.removeAccount(accountNumber);
    }
}