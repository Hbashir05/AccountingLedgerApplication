package com.pluralsight;

import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

import static com.pluralsight.Deposit.addDeposit;
import static com.pluralsight.Deposit.writeToCSV;
import static com.pluralsight.Payment.addPayment;
import static com.pluralsight.Reports.*;

public class Ledger {
    // Create a scanner object for user input
    static Scanner scanner = new Scanner(System.in);
    // Create buffered writer object to write to the csv file
    static BufferedWriter bufferedWriter;
    static BufferedReader bufferedReader;
    static ArrayList <String> entries = new ArrayList<>();

    static {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("ledger1.csv", true));
            bufferedReader = new BufferedReader(new FileReader("ledger1.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Ledger");
        homeScreen();

    }

    public static void homeScreen() {
        // Display the home screen
        System.out.println("1) Add Deposit");
        System.out.println("2) Make Payment");
        System.out.println("3) Ledger");
        System.out.println("4) Exit");

        int choice = scanner.nextInt(); // Get user input
        scanner.nextLine(); // Consume the newline

        if (choice == 1) {
            writeToCSV(addDeposit());
            homeScreen();
        } else if (choice == 2) {
            writeToCSV(addPayment());
            homeScreen();
        } else if (choice == 3) {
            ledgerScreen();
        } else if (choice == 4) {
            try {
                bufferedWriter.close();
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            scanner.close();
        } else {
            System.out.println("Invalid choice");
            homeScreen();
        }
    }

    public static void ledgerScreen() {
        System.out.println("1) All Entries");
        System.out.println("2) Deposits");
        System.out.println("3) Payments");
        System.out.println("4) Reports");
        System.out.println("5) Home");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            allEntries();
            ledgerScreen();
        } else if (choice == 2) {
            viewDeposits();
            ledgerScreen();
        } else if (choice == 3) {
            viewPayments();
            ledgerScreen();
        } else if (choice == 4) {
            viewReports();
        } else if (choice == 5) {
            homeScreen();
        } else {
            System.out.println("Invalid input");
            ledgerScreen();
        }
    }
    public static void sortArray(ArrayList<String> items){
        Collections.reverse(items);
        for (String entry : items){
            System.out.println(entry);
        }
        items.clear();
    }

    public static void allEntries() {
        // Prints all entries to the terminal
        String input;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ledger1.csv"));
            while ((input = bufferedReader.readLine()) != null) {
                entries.add(input);
            }
            sortArray(entries);
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void viewDeposits() {
        // Shows all deposits recorded in the ledger
        String input;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ledger1.csv"));
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split("\\|");
                if (Double.parseDouble(tokens[4]) > 0){
                    entries.add(input);
                }
            }
            sortArray(entries);
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void viewPayments() {
        // Shows all payments recorded in the ledger
        String input;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ledger1.csv"));
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split("\\|");
                if (Double.parseDouble(tokens[4]) < 0){
                   entries.add(input);
                }
            }
            sortArray(entries);
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}