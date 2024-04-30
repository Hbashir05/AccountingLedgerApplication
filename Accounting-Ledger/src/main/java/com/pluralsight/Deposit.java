package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.pluralsight.Ledger.*;

public class Deposit {
    public static String addDeposit() {
        // Prompt for deposit information
        System.out.println("Please enter the deposit information");
        System.out.print("Describe the deposit : ");
        String description = scanner.nextLine();
        System.out.print("Who is the vendor : ");
        String vendor = scanner.nextLine();
        System.out.print("Deposit amount : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        // Return the string
        return (description+"|"+vendor+"|"+amount);
    }

    public static void writeToCSV(String action) {
        // Get the local date and time
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String entry = (date+"|"+time.format(DateTimeFormatter.ofPattern("HH:mm:ss"))+"|"+action+"\n");

        try {
            BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter("ledger1.csv", true));
            // Write the date, time and action to the ledger1.csv file
            bufferedWriter1.write(entry);
            bufferedWriter1.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
