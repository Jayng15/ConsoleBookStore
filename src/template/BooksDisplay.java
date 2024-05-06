package template;

import entity.Book;
import entity.Customer;
import service.adt.interfaces.List;
import service.algorithm.MergeSort;

import java.util.Comparator;
import java.util.Scanner;

public class BooksDisplay {

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayBooks(List<Book> books, Customer customer) {
        MergeSort.sort(books, Comparator.comparing(Book::getPrice));
        boolean validChoice = false;
        int choice = 0;

        while (!validChoice) {
            clearScreen();
            displayer(books);

            // Display options
            System.out.println("Options:");
            System.out.println("1. Choose a book");
            System.out.println("2. Back");
            System.out.print("Enter your choice: ");

            choice = getUserChoice();

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of the book: ");
                    int bookNumber = getUserChoice();
                    if (bookNumber - 1 < books.size()) {
                        Book book = books.get(bookNumber - 1);
                        clearScreen();
                        BookDisplay.displayBook(book, customer, scanner);
                        validChoice = true;
                    } else {
                        System.out.println("Invalid book number");
                    }
                    break;
                case 2:
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
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

    public static void displayer(List<Book> books) {
            System.out.println("List of Books:");
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-5s %-30s %-20s %-10s\n", "No.", "Title", "Author", "Price");
            System.out.println("---------------------------------------------------------------");

            for (int i = 0; i < books.size(); i++) {
                System.out.printf("%-5d %-30s %-20s $%-10.2f\n", i + 1, books.get(i).getTitle(),
                        books.get(i).getAuthor(), books.get(i).getPrice());
            }

            System.out.println("---------------------------------------------------------------");
    }
}
