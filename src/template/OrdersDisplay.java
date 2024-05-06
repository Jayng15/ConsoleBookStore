package template;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

import entity.Book;
import entity.Customer;
import entity.Order;
import service.OrderService;
import service.adt.ArrayListADT;
import service.adt.interfaces.List;
import service.adt.interfaces.Map;

public class OrdersDisplay {

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayOrders(Customer customer, OrderService orderService) {
        List<Order> orders = customer.getOrders().toList();
        boolean continueLoop = true;

        displayer(orders);
        while (continueLoop) {

            System.out.println("Options:");
            System.out.println("1. View order details");
            System.out.println("2. Search order");
            System.out.println("3. Display orders by price");
            System.out.println("4. Go back");
            System.out.print("Enter your choice: ");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    System.out.print("Enter the order number: ");
                    int orderNumber = Integer.parseInt(scanner.nextLine());
                    try {
                        Order order = orders.get(orderNumber - 1);
                        clearScreen();
                        OrderDisplay.displayOrder(order, orderService);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Order not found. Please check the order number.");
                    }
                    break;
                case 2:
                    System.out.print("Enter search criteria: ");
                    String criteria = scanner.nextLine();
                    // Add search logic or a method call here
                    break;
                case 3:
                    orderService.sortOrdersByPrice(orders);
                    displayer(orders);
                    break;
                case 4:
                    continueLoop = false; // Exit loop
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

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void displayer(List<Order> orders) {
        System.out.println("Your Orders:");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("%-20s %-30s %-15s %-10s\n", "Order Number", "Date", "Status", "Total");
        System.out.println("------------------------------------------------------------------------------");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.printf("%-20d %-30s %-15s $%-10.2f\n", i + 1, order.getOrderDate(),
                    order.getStatus(), order.calculateTotalPrice());

        }
        System.out.println("------------------------------------------------------------------------------");
    }
}
