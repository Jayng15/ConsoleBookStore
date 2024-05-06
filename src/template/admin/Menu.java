package template.admin;

import java.util.Comparator;
import java.util.Scanner;

import entity.Book;
// import entity.Customer;
import entity.Order;
import entity.enums.ShippingInfo.ShippingStatus;
import service.BookService;
import service.CustomerService;
import service.OrderService;
import service.adt.interfaces.List;
import service.algorithm.LinearSearch;
import service.algorithm.MergeSort;
import service.algorithm.helper.BookValueExtractor;
import service.algorithm.helper.OrderValueExtractor;
import template.BookDisplay;
import template.BooksDisplay;
import template.OrderDisplay;
import template.OrdersDisplay;
import template.Search;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayAdminMenu(OrderService orderService, CustomerService customerService,
            BookService bookService) {
        displayMenu(orderService, customerService, bookService);
    }

    private static void displayMenu(OrderService orderService, CustomerService customerService,
            BookService bookService) {
        boolean exit = false;
        while (!exit) {
            clearScreen();
            System.out.println("╔════════════════════════════════════════════════════╗");
            System.out.println("║                Admin Main Menu                     ║");
            System.out.println("╠════════════════════════════════════════════════════╣");
            System.out.println("║ Options:                                           ║");
            System.out.println("║        1. Manage Books                             ║");
            System.out.println("║        2. Manage Orders                            ║");
            System.out.println("║        3. Exit                                     ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    clearScreen();
                    manageBooks(bookService.getBooks(), bookService);
                    break;
                case 2:
                    clearScreen();
                    manageOrders(orderService);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
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

    private static void manageBooks(List<Book> books, BookService bookService) {
        boolean backToMenu = false;
        while (!backToMenu) {
            System.out.println("\n═════ Manage Books ═════");
            BooksDisplay.displayer(books);
            System.out.println("Options:");
            System.out.println("1. View a book detail");
            System.out.println("2. Search a book");
            System.out.println("3. Create a book");
            System.out.println("4. Remove a book");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    viewBook(bookService);
                    break;
                case 2:
                    clearScreen();
                    searchBookMenu(bookService);
                    break;
                case 3:
                    createBook(bookService);
                    break;
                case 4:
                    removeBook(books, bookService);
                    break;
                case 5:
                    backToMenu = true;
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    private static void manageOrders(OrderService orderService) {
        List<Order> orders = orderService.getOrders().toList();
        System.out.println("\n══════ Manage Orders ══════");
        OrdersDisplay.displayer(orders);
        System.out.println("Options:");
        System.out.println("1. Search order");
        System.out.println("2. View order details");
        System.out.println("3. Back");
        System.out.print("Enter your choice: ");

        int choice = getUserChoice();

        switch (choice) {
            case 1:
                searchOrderMenu(orderService);
                break;
            case 2:
                System.out.print("Enter order number: ");
                int orderNumber = getUserChoice();
                Order order = orders.get(orderNumber - 1);
                if (order != null) {
                    clearScreen();
                    OrderDisplay.displayer(order);
                    displayOrderOptions(order);
                } else {
                    System.out.println("Invalid order");
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void searchBookMenu(BookService bookService) {
        int option = 0;
        Search.displaySearchOptions();
        option = getUserChoice();
        switch (option) {
            case 1:
                System.out.print("Enter the Title: ");
                String title = scanner.nextLine();
                searchBook(bookService.getBooks(), title, "title", bookService);
                break;
            case 2:
                System.out.print("Enter the ISBN: ");
                String ISBN = scanner.nextLine();
                searchBook(bookService.getBooks(), ISBN, "isbn", bookService);
                break;
            case 3:
                System.out.print("Enter the Author: ");
                String author = scanner.nextLine();
                searchBook(bookService.getBooks(), author, "author", bookService);
                break;
            case 4:
                break;
            default:
                System.out.print("Invalid option. Please try again.");
                break;
        }
    }

    private static void createBook(BookService bookService) {
        System.out.println("═════ Create a Book ═════");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Book newBook = new Book(title, author, isbn, price, description);

        bookService.addBook(newBook);

        System.out.println("Book created successfully:");
        System.out.println(newBook);

    }

    private static void removeBook(List<Book> books, BookService bookService) {
        System.out.println("═════ Remove a Book ═════");
        System.out.print("Enter the number of the book you want to remove: ");
        int bookNumber = getUserChoice();
        Book book = books.get(bookNumber - 1);
        if (book != null) {
            System.out.print("Are you sure you want to remove this book? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("yes")) {
                books.remove(book);
                // bookService.removeBook(book);
                System.out.println(book.getTitle() + " removed successfully.");
            } else {
                System.out.println("Removal cancelled.");
            }
        } else {
            System.out.println("Book not founded.");
        }

    }

    private static void searchBook(List<Book> books, String value, String criteria, BookService bookService) {
        LinearSearch<Book> binarySearch = new LinearSearch<>();
        BookValueExtractor bookExtractor = new BookValueExtractor();
        List<Book> foundedBooks = binarySearch.search(books, criteria, value, bookExtractor);
        if (foundedBooks != null) {
            MergeSort.sort(foundedBooks, Comparator.comparing(Book::getPrice));
            BooksDisplay.displayer(foundedBooks);
            manageBooks(foundedBooks, bookService);
            // scanner.nextLine();
        } else {
            System.out.print("No book to be found");
            // scanner.nextLine();
        }

    }

    private static void viewBook(BookService bookService) {
        System.out.print("Enter the number of the book you want to check: ");
        int bookNumber = getUserChoice();
        Book book = bookService.getBooks().get(bookNumber - 1);
        if (book != null) {
            clearScreen();
            BookDisplay.menuDisplayer(book);
            System.out.print("1. Change book detail     ");
            System.out.print("2. Remove book     ");
            System.out.println("3. Go Back");

            System.out.print("Enter your choice: ");
            int choice;
            do {
                choice = getUserChoice();
                switch (choice) {
                    case 1:
                        changeBook(book);
                        break;
                    case 2:
                        System.out.print("Are you sure you want to remove this book? (yes/no): ");
                        String confirm = scanner.nextLine().trim().toLowerCase();

                        if (confirm.equals("yes")) {
                            bookService.getBooks().remove(book);
                            // bookService.removeBook(book);
                            System.out.println(book.getTitle() + " removed successfully.");
                        } else {
                            System.out.println("Removal cancelled.");
                        }
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid choice please enter a number between 1 and 3.");
                        break;
                }
            } while (choice < 1 && choice > 3);
        }
    }

    private static void searchOrderMenu(OrderService orderService) {
        System.out.print("Enter the customer name: ");
        String customerStatus = scanner.nextLine();
        searchOrder(orderService.getOrders().toList(), "customerName", customerStatus);
        // int choice = getUserChoice();
    }

    private static void searchOrder(List<Order> orders, String criteria, String value) {
        LinearSearch<Order> linearSearch = new LinearSearch<>();
        OrderValueExtractor orderExtractor = new OrderValueExtractor();
        List<Order> foundedOders = linearSearch.search(orders, criteria, value, orderExtractor);
        if (foundedOders != null) {
            OrdersDisplay.displayer(foundedOders);
            System.out.println("Options:");
            System.out.println("1. View order details");
            System.out.println("2. Back");
            System.out.print("Enter your choice: ");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    System.out.print("Enter order number: ");
                    int orderNumber = getUserChoice();
                    Order order = foundedOders.get(orderNumber - 1);
                    if (order != null) {
                        clearScreen();
                        OrderDisplay.displayer(order);
                        displayOrderOptions(order);
                    } else {
                        System.out.println("Invalid order");
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } else {
            System.out.print("Cannot find any order with that customer name.");
            scanner.nextLine();
        }
    }

    private static void changeBook(Book book) {
        System.out.print("Change book title: ");
        String title = scanner.nextLine();
        System.out.print("Change book author: ");
        String author = scanner.nextLine();
        System.out.print("Change book isbn: ");
        String isbn = scanner.nextLine();
        System.out.print("Change book price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Change book description: ");
        String des = scanner.nextLine();
        book.setAuthor(author);
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setDescription(des);
    }

    private static void displayOrderOptions(Order order) {
        System.out.println("Options:");
        System.out.println("1. Change order status");
        System.out.println("2. Back");
        System.out.print("Enter your choice: ");

        int choice = getUserChoice();

        switch (choice) {
            case 1:
                System.out.println("1. PENDING");
                System.out.println("2. IN_TRANSIT");
                System.out.println("3. DELIVERED");
                System.out.print("Enter shipping method: ");

                ShippingStatus shippingStatus = null;
                boolean validInput = false;

                while (!validInput) {
                    int option = Integer.parseInt(scanner.nextLine());
                    switch (option) {
                        case 1:
                            shippingStatus = ShippingStatus.PENDING;
                            validInput = true;
                            break;
                        case 2:
                            shippingStatus = ShippingStatus.IN_TRANSIT;
                            validInput = true;
                            break;
                        case 3:
                            shippingStatus = ShippingStatus.DELIVERED;
                            validInput = true;
                        default:
                            System.out.println("Invalid input. Please enter a number between 1 and 3.");
                            break;
                    }
                }
                order.getShippingInfo().setShippingStatus(shippingStatus);
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }

}
