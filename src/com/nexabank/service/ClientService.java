package com.nexabank.service;

import com.nexabank.model.Client;

public class ClientService {

    public void updateClient(
            Client client,
            String firstName,
            String lastName,
            String email
    ) {

        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setEmail(email);
    }
}