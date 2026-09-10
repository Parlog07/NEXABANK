package com.nexabank.model;

import java.util.HashMap;

public class Client extends Person{
    private String idClient;
    private HashMap<String, Account> accounts;

    public Client(String idClient,
                  String lastName,
                  String firstName,
                  String email,
                  String password) {

        super(lastName, firstName, email, password);

        this.idClient = idClient;
        this.accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void removeAccount(String accountNumber) {
        accounts.remove(accountNumber);
    }
}
