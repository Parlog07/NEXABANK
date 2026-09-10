package com.nexabank.model;

public class Manager extends Person {

    private String idManager;

    public Manager(
            String idManager,
            String lastName,
            String firstName,
            String email,
            String password
    ) {
        super(lastName, firstName, email, password);
        this.idManager = idManager;
    }

    public String getIdManager() {
        return idManager;
    }

    public void setIdManager(String idManager) {
        this.idManager = idManager;
    }
}