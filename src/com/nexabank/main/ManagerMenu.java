package com.nexabank.main;

import com.nexabank.model.Account;
import com.nexabank.model.AccountType;
import com.nexabank.model.Client;
import com.nexabank.service.AccountService;
import com.nexabank.service.ClientService;
import com.nexabank.utils.StatementFileManager;

import java.io.IOException;
import java.util.Scanner;

public class ManagerMenu {

    public static void show(
            Scanner scanner,
            Client client,
            AccountService accountService,
            ClientService clientService,
            StatementFileManager statementFileManager
    ) {

        int choice;

        do {

            System.out.println("\n===== MANAGER MENU =====");
            System.out.println("1. Create account");
            System.out.println("2. Modify account");
            System.out.println("3. Close account");
            System.out.println("4. Update client information");
            System.out.println("5. View client bank statement");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    createAccount(
                            scanner,
                            client,
                            accountService
                    );
                    break;

                case 2:
                    modifyAccount(
                            scanner,
                            client,
                            accountService
                    );
                    break;

                case 3:
                    closeAccount(
                            scanner,
                            client,
                            accountService
                    );
                    break;

                case 4:
                    updateClient(
                            scanner,
                            client,
                            clientService
                    );
                    break;

                case 5:
                    viewStatement(
                            scanner,
                            client,
                            statementFileManager
                    );
                    break;

                case 0:
                    System.out.println("Back to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }


    private static void createAccount(
            Scanner scanner,
            Client client,
            AccountService accountService
    ) {

        System.out.print("Enter new account number: ");
        String accountNumber = scanner.next();

        System.out.println("Choose account type:");
        System.out.println("1. CURRENT");
        System.out.println("2. SAVINGS");
        System.out.print("Choice: ");

        int typeChoice = scanner.nextInt();

        AccountType accountType;

        if (typeChoice == 1) {

            accountType = AccountType.CURRENT;

        } else if (typeChoice == 2) {

            accountType = AccountType.SAVINGS;

        } else {

            System.out.println("Invalid account type.");
            return;
        }

        accountService.createAccount(
                client,
                accountNumber,
                accountType
        );

        System.out.println("Account created successfully.");
    }


    private static void modifyAccount(
            Scanner scanner,
            Client client,
            AccountService accountService
    ) {

        try {

            System.out.print("Enter account number: ");
            String accountNumber = scanner.next();

            System.out.println("Choose new account type:");
            System.out.println("1. CURRENT");
            System.out.println("2. SAVINGS");
            System.out.print("Choice: ");

            int typeChoice = scanner.nextInt();

            AccountType newType;

            if (typeChoice == 1) {

                newType = AccountType.CURRENT;

            } else if (typeChoice == 2) {

                newType = AccountType.SAVINGS;

            } else {

                System.out.println("Invalid account type.");
                return;
            }

            accountService.modifyAccountType(
                    client,
                    accountNumber,
                    newType
            );

            System.out.println("Account updated successfully.");

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }


    private static void closeAccount(
            Scanner scanner,
            Client client,
            AccountService accountService
    ) {

        try {

            System.out.print("Enter account number to close: ");
            String accountNumber = scanner.next();

            accountService.closeAccount(
                    client,
                    accountNumber
            );

            System.out.println("Account closed successfully.");

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }


    private static void updateClient(
            Scanner scanner,
            Client client,
            ClientService clientService
    ) {

        System.out.print("Enter new first name: ");
        String firstName = scanner.next();

        System.out.print("Enter new last name: ");
        String lastName = scanner.next();

        System.out.print("Enter new email: ");
        String email = scanner.next();

        clientService.updateClient(
                client,
                firstName,
                lastName,
                email
        );

        System.out.println(
                "Client information updated successfully."
        );
    }

    private static void viewStatement(
            Scanner scanner,
            Client client,
            StatementFileManager statementFileManager
    ) {

        try {

            System.out.print("Enter account number: ");
            String accountNumber = scanner.next();

            Account account =
                    client.findAccount(accountNumber);

            if (account == null) {

                System.out.println("Account not found.");
                return;
            }

            System.out.println(
                    "\n===== BANK STATEMENT ====="
            );

            statementFileManager.readStatement(account);

        } catch (IOException e) {

            System.out.println(
                    "File error: " + e.getMessage()
            );
        }
    }
}