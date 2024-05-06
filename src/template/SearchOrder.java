package template;

import java.util.Scanner;

public class SearchOrder {

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        clearScreen();
        System.out.println("──────────────────────────────────");
        System.out.println("             Search Options       ");
        System.out.println("──────────────────────────────────");
        System.out.println("1. Search by book");
        System.out.println("2. Search by ID");
        System.out.println("3. Return");
        System.out.println("──────────────────────────────────");
        System.out.print("Enter your choice: ");
    }

    public static int getUserChoice() {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }

    public static void searchByBook() {
        clearScreen();
        System.out.println("Searching by book...");
    }

    public static void searchById() {
        clearScreen();
        System.out.println("Searching by ID...");
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
