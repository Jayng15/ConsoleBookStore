package template.user;

import java.util.Scanner;

import entity.Cart;
import entity.Customer;
import entity.Order;
import service.BookService;
import service.OrderService;
import template.BooksDisplay;
import template.OrdersDisplay;
import template.Search;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuDisplay(BookService bookService, Customer customer, OrderService orderService) {
        boolean running = true;

        while (running) {
            clearScreen();
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    clearScreen();
                    Search.displaySearchMenu(bookService, customer);
                    break;
                case 2:
                    clearScreen();
                    CartDisplay.displayCart(customer.getCart(), customer, orderService);
                    break;
                case 3:
                    OrdersDisplay.displayOrders(customer, orderService);
                    clearScreen();
                    break;
                case 4:
                    BooksDisplay.displayBooks(bookService.getBooks(), customer);
                    clearScreen();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                               AV BookStore                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Search for a book                                                       ║");
        System.out.println("║ 2. Display my cart                                                         ║");
        System.out.println("║ 3. Display my orders                                                       ║");
        System.out.println("║ 4. Display all books in the store                                          ║");
        System.out.println("║ 5. Exit                                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
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

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}


