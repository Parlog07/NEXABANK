package com.nexabank.utils;

import com.nexabank.exception.FileAccessException;
import com.nexabank.model.Account;
import com.nexabank.model.Transaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StatementFileManager {

    public void saveTransaction(
            Account account,
            Transaction transaction
    ) throws FileAccessException {

        String fileName =
                "statement_"
                        + account.getAccountNumber()
                        + ".txt";

        File file = new File(fileName);

        boolean newFile =
                !file.exists()
                        || file.length() == 0;

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(file, true)
                        )
        ) {

            if (newFile) {

                writer.write(
                        "Date | Type | Amount | Source Account | Destination Account"
                );

                writer.newLine();

                writer.write(
                        "-------------------------------------------------------------"
                );

                writer.newLine();
            }

            writer.write(
                    transaction.getDate()
                            + " | "
                            + transaction.getType()
                            + " | "
                            + transaction.getAmount()
                            + " | "
                            + getAccountNumber(
                            transaction.getSourceAccount()
                    )
                            + " | "
                            + getAccountNumber(
                            transaction.getDestinationAccount()
                    )
            );

            writer.newLine();

        } catch (IOException e) {

            throw new FileAccessException(
                    "Unable to save the transaction file."
            );
        }
    }


    public void readStatement(Account account)
            throws FileAccessException {

        String fileName =
                "statement_"
                        + account.getAccountNumber()
                        + ".txt";

        File file = new File(fileName);

        if (!file.exists()) {

            System.out.println(
                    "No transactions found for this account."
            );

            return;
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

            String line;

            while (
                    (line = reader.readLine()) != null
            ) {

                System.out.println(line);
            }

        } catch (IOException e) {

            throw new FileAccessException(
                    "Unable to read the bank statement."
            );
        }
    }


    private String getAccountNumber(Account account) {

        if (account == null) {
            return "-";
        }

        return account.getAccountNumber();
    }
}