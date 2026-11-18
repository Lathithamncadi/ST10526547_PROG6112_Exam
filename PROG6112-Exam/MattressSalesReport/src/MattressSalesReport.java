
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
public class MattressSalesReport {
     private static final String[]PRODUCTS = {
         "Cozy Nights Monarch mattress", "Restoric Barbados Mattress", "Sealy Phuket Mattress"
    };
    private static final int MONTHS = 3;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] sales = new int[PRODUCTS.length][MONTHS];
        int[] productTotals = new int[PRODUCTS.length];
        int grandTotal = 0;

        // Collect sales figures from the user
        for (int p = 0; p < PRODUCTS.length; p++) {
            System.out.println();
            System.out.println("--- " + PRODUCTS[p] + " ---");
            for (int m = 0; m < MONTHS; m++) {
                sales[p][m] = readSales(input, m + 1);
                productTotals[p] += sales[p][m];
            }
            grandTotal += productTotals[p];
        }

        // Average product sales = total units / (products x months)
        double averageProductSales = (double) grandTotal / (PRODUCTS.length * MONTHS);

        // Most popular product = highest total sales
        int topIndex = 0;
        for (int p = 1; p < PRODUCTS.length; p++) {
            if (productTotals[p] > productTotals[topIndex]) {
                topIndex = p;
            }
        }

        printReport(sales, productTotals, grandTotal, averageProductSales, topIndex);
        input.close();
    }

    // Prompts until a valid whole number (0 or more) is entered
    private static int readSales(Scanner input, int month) {
        while (true) {
            System.out.print("Enter number of sales for month " + month + ": ");
            String text = input.nextLine().trim();
            try {
                int value = Integer.parseInt(text);
                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // fall through to the error message
            }
            System.out.println("Invalid input. Please enter a whole number (0 or more).");
        }
    }

    private static void printReport(int[][] sales, int[] productTotals, int grandTotal,
                                    double averageProductSales, int topIndex) {

        System.out.println("-".repeat(68));
        System.out.println();
        System.out.println("Total number of products sold   : " + grandTotal);
        System.out.printf("Average product sales          : %.2f%n", averageProductSales);
        System.out.println("Most popular product            : " + PRODUCTS[topIndex]
                + " (" + productTotals[topIndex] + " units sold)");
    }
}