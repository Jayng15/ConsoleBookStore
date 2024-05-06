package template;

import java.util.Scanner;

import entity.Customer;
import service.BookService;


public class Search {
    private static final Scanner scanner = new Scanner(System.in);

    public static void displaySearchMenu(BookService bookService, Customer customer) {
        int option = 0;
        do {
            clearScreen();
            displaySearchOptions();
            option = getUserOption();
            switch (option) {
                case 1:
                    searchByTitle(bookService, customer);
                    break;
                case 2:
                    searchByISBN(bookService, customer);
                    break;
                case 3:
                    searchByAuthor(bookService, customer);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (option < 4);
    }


    private static int getUserOption() {
        int option = 0;
        while (true) {
            try {
                option = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return option;
    }

    public static void displaySearchOptions() {
    System.out.println("╔══════════════════════════════════════╗");
    System.out.println("║             Search Options           ║");
    System.out.println("╠══════════════════════════════════════╣");
    System.out.println("║ 1. Search by Title                   ║");
    System.out.println("║ 2. Search by ISBN                    ║");
    System.out.println("║ 3. Search by Author                  ║");
    System.out.println("║ 4. Return                            ║");
    System.out.println("╚══════════════════════════════════════╝");
    System.out.print("Enter your choice: ");
    }

    private static void searchByTitle(BookService bookService, Customer customer) {
        System.out.print("Enter the title to search for: ");
        String title = scanner.nextLine();
        bookService.findBookByTitle(title, customer);
    }

    private static void searchByISBN(BookService bookService, Customer customer) {
        System.out.print("Enter the ISBN to search for: ");
        String isbn = scanner.nextLine();
        bookService.findBooksByISBN(isbn, customer);
    }

    private static void searchByAuthor(BookService bookService, Customer customer) {
        System.out.print("Enter the author to search for: ");
        String author = scanner.nextLine();
        bookService.findBooksByAuthor(author, customer);
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}


