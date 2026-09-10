package com.nexabank.model;

public abstract class Person {
    private string lastname;
    private string firstname;
    private string email;
    private string password;

    public Person(String lastName, String firstName, String email, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public string getLastname() {
        return lastname;
    }

    public void setLastname(string lastname) {
        this.lastname = lastname;
    }

    public string getFirstname() {
        return firstname;
    }

    public void setFirstname(string firstname) {
        this.firstname = firstname;
    }

    public string getEmail() {
        return email;
    }

    public void setEmail(string email) {
        this.email = email;
    }

    public string getPassword() {
        return password;
    }

    public void setPassword(string password) {
        this.password = password;
    }
}
