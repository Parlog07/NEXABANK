package com.nexabank.main;

import com.nexabank.model.Account;
import com.nexabank.model.AccountType;
import com.nexabank.model.Client;
import com.nexabank.service.AccountService;
import com.nexabank.service.BankService;
import com.nexabank.service.ClientService;
import com.nexabank.utils.StatementFileManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BankService bankService = new BankService();
        AccountService accountService = new AccountService();
        ClientService clientService = new ClientService();
        StatementFileManager statementFileManager =
                new StatementFileManager();

        Client client = new Client(
                "CL001",
                "Doe",
                "John",
                "john@nexabank.com",
                "1234"
        );

        Account currentAccount = new Account(
                "C1001",
                AccountType.CURRENT
        );

        Account savingsAccount = new Account(
                "C1002",
                AccountType.SAVINGS
        );

        client.addAccount(currentAccount);
        client.addAccount(savingsAccount);

        int choice;

        do {

            System.out.println("\n===== NEXABANK =====");
            System.out.println("1. Client");
            System.out.println("2. Manager");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    ClientMenu.show(
                            scanner,
                            client,
                            bankService,
                            statementFileManager
                    );
                    break;

                case 2:
                    ManagerMenu.show(
                            scanner,
                            client,
                            accountService,
                            clientService,
                            statementFileManager
                    );
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }
}