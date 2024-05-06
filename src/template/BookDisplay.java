package template;

import java.util.Scanner;

import entity.Book;
import entity.Customer;

public class BookDisplay {

    public static void displayBook(Book book, Customer customer, Scanner scanner) {
        menuDisplayer(book);
        System.out.print("1. Add to Cart     ");
        System.out.println("2. Go Back");

        int choice;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter quantity: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        customer.getCart().addItem(book, quantity);
                        System.out.println("Successfully added \"" + book.getTitle() + "\"");
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = 0; // Reset choice to allow the loop to continue
            }
        } while (choice < 1 || choice > 2);

    }

    public static void menuDisplayer(Book book){
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                  Book Details                                   ║");
        System.out.println("╠═════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Title: " + formatText(book.getTitle(), 73));
        System.out.println("║ Author: " + formatText(book.getAuthor(), 72));
        System.out.println("║ ISBN: " + formatText(book.getIsbn(), 74));
        System.out.println("║ Price: $" + formatText(String.valueOf(book.getPrice()), 72));
        System.out.println("║ Description: " + formatText(book.getDescription(), 67));
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════╝");
    }

    private static String formatText(String text, int width) {
        StringBuilder formattedText = new StringBuilder();
        int startIndex = 0;
        while (startIndex < text.length()) {
            int endIndex = Math.min(startIndex + width, text.length());
            formattedText.append(text.substring(startIndex, endIndex));
            if (endIndex < text.length()) {
                formattedText.append("\n").append("║ ");
            } else {
                int remainingSpace = startIndex + width - endIndex;
                for (int i = 0; i < remainingSpace; i++) {
                    formattedText.append(" ");
                }
                formattedText.append("║ ");
            }
            startIndex = endIndex;
        }
        return formattedText.toString();
    }
}
