package template.user;

import java.util.Scanner;

import entity.Book;
import entity.Cart;
import entity.Customer;
import entity.Payment;
import entity.ShippingInfo;
import service.CartService;
import service.OrderService;

public class CartDisplay {

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayCart(Cart cart, Customer customer, OrderService orderService) {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("Your Cart:");
            System.out.println("------------------------------------------------------------------------------");
            System.out.printf("%-4s %-30s %-20s %-12s %-12s\n", "No.", "Title", "Author", "Price", "Quantity");
            System.out.println("------------------------------------------------------------------------------");
            int i = 1;
            for (Book book : cart.getItems().keySet()) {
                int quantity = cart.getItems().get(book);
                System.out.printf("%-4d %-30s %-20s $%-10.2f %-10d\n", i++, book.getTitle(), book.getAuthor(), book.getPrice(), quantity);
            }
        
            System.out.println("------------------------------------------------------------------------------");
            System.out.printf("%-56s $%-10.2f\n", "Total:", cart.calculateTotalPrice());
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Options:");
            System.out.println("1. Create an order");
            System.out.println("2. Remove a book");
            System.out.println("3. Change quantity");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    ShippingInfo shippingInfo = ShippingInfo.promptShippingInfo(scanner);
                    Payment paymentInfo = Payment.promptPaymentInfo(scanner);
                    orderService.addOrder(customer, cart, shippingInfo, paymentInfo);
                    break;
                case 2:
                    System.out.print("Enter the position of the book to remove: ");
                    int positionToRemove = Integer.parseInt(scanner.nextLine());
                    CartService.removeBook(cart, positionToRemove);
                    break;
                case 3:
                    System.out.print("Enter the position of the book you want to change quantity for: ");
                    int selectedIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    CartService.changeQuantity(cart, selectedIndex);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    exit = true;
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
}
