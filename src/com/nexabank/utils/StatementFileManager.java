package com.nexabank.utils;

import com.nexabank.model.Account;
import com.nexabank.model.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;

public class StatementFileManager {

    public void saveTransaction(Account account, Transaction transaction) throws IOException {

        String fileName = "statement_" + account.getAccountNumber() + ".txt";

        BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName, true)
        );

        writer.write(
                transaction.getDate()
                        + " | "
                        + transaction.getType()
                        + " | "
                        + transaction.getAmount()
                        + " | "
                        + getAccountNumber(transaction.getSourceAccount())
                        + " | "
                        + getAccountNumber(transaction.getDestinationAccount())
        );

        writer.newLine();
        writer.close();
    }

    private String getAccountNumber(Account account) {
        if (account == null) {
            return "null";
        }

        return account.getAccountNumber();
    }

    public void readStatement(Account account) throws IOException {

        String fileName = "statement_" + account.getAccountNumber() + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}