package com.pluralsight;

import static com.pluralsight.Ledger.scanner;

public class Payment {
    public static String addPayment() {
        // Prompt for payment information
        System.out.println("Please enter the payment information");
        System.out.print("Describe the purchased item : ");
        String item = scanner.nextLine();
        System.out.print("Name of vendor : ");
        String vendor = scanner.nextLine();
        System.out.print("Enter the cost of the item as a negative number : ");
        double cost = scanner.nextDouble();
        scanner.nextLine(); // Consume to newline
        // Return string
        return (item + "|" + vendor + "|" + cost);
    }
}
