package com.nexabank.main;

import com.nexabank.exception.FileAccessException;
import com.nexabank.model.Account;
import com.nexabank.model.Client;
import com.nexabank.service.BankService;
import com.nexabank.utils.StatementFileManager;

import java.util.Scanner;

public class ClientMenu {

    public static void show(
            Scanner scanner,
            Client client,
            BankService bankService,
            StatementFileManager statementFileManager
    ) {

        int choice;

        do {

            System.out.println("\n===== CLIENT MENU =====");
            System.out.println("1. View balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View bank statement");
            System.out.println("0. Back");

            choice = readInt(
                    scanner,
                    "Choose an option: "
            );

            switch (choice) {

                case 1:
                    viewBalance(scanner, client);
                    break;

                case 2:
                    deposit(
                            scanner,
                            client,
                            bankService
                    );
                    break;

                case 3:
                    withdraw(
                            scanner,
                            client,
                            bankService
                    );
                    break;

                case 4:
                    transfer(
                            scanner,
                            client,
                            bankService
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
                    System.out.println(
                            "Back to main menu..."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 0);
    }

    private static void viewBalance(
            Scanner scanner,
            Client client
    ) {

        System.out.print(
                "Enter account number: "
        );

        String accountNumber =
                scanner.nextLine().trim();

        Account account =
                client.findAccount(accountNumber);

        if (account == null) {

            System.out.println(
                    "Account not found."
            );

            return;
        }

        System.out.println(
                "Balance: "
                        + account.getBalance()
        );
    }

    private static void deposit(
            Scanner scanner,
            Client client,
            BankService bankService
    ) {

        try {

            System.out.print(
                    "Enter account number: "
            );

            String accountNumber =
                    scanner.nextLine().trim();

            Account account =
                    client.findAccount(accountNumber);

            if (account == null) {

                System.out.println(
                        "Account not found."
                );

                return;
            }

            double amount = readDouble(
                    scanner,
                    "Enter amount: "
            );

            bankService.deposit(
                    account,
                    amount
            );

            System.out.println(
                    "Deposit successful."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: "
                            + e.getMessage()
            );
        }
    }

    private static void withdraw(
            Scanner scanner,
            Client client,
            BankService bankService
    ) {

        try {

            System.out.print(
                    "Enter account number: "
            );

            String accountNumber =
                    scanner.nextLine().trim();

            Account account =
                    client.findAccount(accountNumber);

            if (account == null) {

                System.out.println(
                        "Account not found."
                );

                return;
            }

            double amount = readDouble(
                    scanner,
                    "Enter amount: "
            );

            bankService.withdraw(
                    account,
                    amount
            );

            System.out.println(
                    "Withdrawal successful."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: "
                            + e.getMessage()
            );
        }
    }

    private static void transfer(
            Scanner scanner,
            Client client,
            BankService bankService
    ) {

        try {

            System.out.print(
                    "Source account number: "
            );

            String sourceNumber =
                    scanner.nextLine().trim();

            System.out.print(
                    "Destination account number: "
            );

            String destinationNumber =
                    scanner.nextLine().trim();

            Account source =
                    client.findAccount(sourceNumber);

            Account destination =
                    client.findAccount(destinationNumber);

            if (
                    source == null
                            || destination == null
            ) {

                System.out.println(
                        "Account not found."
                );

                return;
            }

            double amount = readDouble(
                    scanner,
                    "Enter amount: "
            );

            bankService.transfer(
                    source,
                    destination,
                    amount
            );

            System.out.println(
                    "Transfer successful."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: "
                            + e.getMessage()
            );
        }
    }

    private static void viewStatement(
            Scanner scanner,
            Client client,
            StatementFileManager statementFileManager
    ) {

        try {

            System.out.print(
                    "Enter account number: "
            );

            String accountNumber =
                    scanner.nextLine().trim();

            Account account =
                    client.findAccount(accountNumber);

            if (account == null) {

                System.out.println(
                        "Account not found."
                );

                return;
            }

            statementFileManager
                    .readStatement(account);

        } catch (FileAccessException e) {

            System.out.println(
                    "File error: "
                            + e.getMessage()
            );
        }
    }

    private static int readInt(
            Scanner scanner,
            String message
    ) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    private static double readDouble(
            Scanner scanner,
            String message
    ) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return Double.parseDouble(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid amount. Please enter a valid number."
                );
            }
        }
    }
}