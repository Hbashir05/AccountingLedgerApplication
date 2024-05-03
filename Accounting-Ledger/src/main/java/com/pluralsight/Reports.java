package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static com.pluralsight.Ledger.*;

public class Reports {

    static LocalDate todayDate = LocalDate.now();
    static int thisMonth = todayDate.getMonthValue();
    static int thisYear = todayDate.getYear();
    static ArrayList <String> entries = new ArrayList<>();

    public static void viewReports(){
        //Prompt the user to view Reports
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("6) Back to Ledger Screen");
        System.out.println("7) Back to Home Screen");


        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice==1) {
            monthToDate();
            viewReports();
        } else if (choice==2) {
            previousMonth();
            viewReports();
        } else if (choice==3) {
            yearToDate();
            viewReports();
        } else if (choice==4) {
            previousYear();
            viewReports();
        } else if (choice==5) {
            searchByVendor();
            viewReports();
        } else if (choice==6) {
            ledgerScreen();
        } else if (choice==7) {
            homeScreen();
        } else {
            System.out.println("Invalid input");

        }
    }
    // Displays all entries from the current month
    public static void monthToDate() {
        String input;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ledger1.csv"));
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split("\\|");
                String[] date = tokens[0].split("-");
                if (Double.parseDouble(date[1]) == thisMonth && Double.parseDouble(date[0]) == thisYear) {
                    entries.add(input);
                    viewReports();
                }
            }
            sortArray(entries);
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Sorts, prints and clears the ArrayList
    public static void sortArray(ArrayList<String> items){
        Collections.reverse(items);
        for (String entry : items){
            System.out.println(entry);
        }
        items.clear();
    }

    // Displays all entries from the past month
    public static void previousMonth() {
        String input;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ledger1.csv"));
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split("\\|");
                String[] date = tokens[0].split("-");
                if (Double.parseDouble(date[1]) == thisMonth-1 && Integer.parseInt(date[0]) == thisYear) {
                    entries.add(input);
                }
            }
            sortArray(entries);
            bufferedReader.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    // Display all entries from the current year
    public static void yearToDate() {
        String input;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ledger1.csv"));
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split("\\|");
                String[] date = tokens[0].split("-");
                if (Integer.parseInt(date[0]) == thisYear) {
                    entries.add(input);
                }
            }
            sortArray(entries);
            bufferedReader.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    // Display all entries from the previous year
    public static void previousYear() {
        String input;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ledger1.csv"));
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split("\\|");
                String[] date = tokens[0].split("-");
                if (Integer.parseInt(date[0]) == thisYear -1) {
                    entries.add(input);
                }
            }
            sortArray(entries);
            bufferedReader.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    // Displays all entries from a specific vendor
    public static void searchByVendor() {
        // Prompt user for the name of the vendor
        System.out.print("Please enter the name of the vendor : ");
        String vendor = scanner.nextLine();

        String input;
        // Read and query the csv file for entries from that vendor
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ledger1.csv"));
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split("\\|");
                if (tokens[3].equalsIgnoreCase(vendor)) {
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
