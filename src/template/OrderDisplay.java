package template;

import entity.Book;
import entity.Order;
import entity.enums.Payment.PaymentMethod;
import entity.enums.ShippingInfo.ShippingMethod;
import entity.enums.ShippingInfo.ShippingStatus;
import service.OrderService;
import service.adt.interfaces.Map;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class OrderDisplay {

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayOrder(Order order, OrderService orderService) {
        displayer(order);
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // System.out.println("Order Details:");
        // System.out.println("-----------------------------------------------------------");
        // System.out.println("Order ID: " + order.getOrderId());
        // System.out.println("Customer: " + order.getCustomer().getName());
        // System.out.println("Order Date: " + dateFormat.format(order.getOrderDate()));
        // System.out.println("Shipping Info:");
        // System.out.println("  Method: " + order.getShippingInfo().getShippingMethod());
        // System.out.println("  Cost: $" + order.getShippingInfo().getShippingCost());
        // System.out.println("  Address: " + order.getShippingInfo().getShippingAddress());
        // System.out.println("  Status: " + order.getShippingInfo().getShippingStatus());
        // System.out.println("Payment Info:");
        // System.out.println("  Method: " + order.getPaymentInfo().getPaymentMethod());
        // System.out.println("  Status: " + order.getPaymentInfo().getPaymentStatus());
        // System.out.println("Order Items:");
        // System.out.println("  (Title)                (Price)    (Quantity)");
        // for (Map.Entry<Book, Integer> entry : order.getOrderItems().entrySet()) {
        //     Book book = entry.getKey();
        //     int quantity = entry.getValue();
        //     System.out.printf("  %-22s $%-13.2f %-3d\n", book.getTitle(), book.getPrice(), quantity);
        // }
        // System.out.println("-----------------------------------------------------------");
        // System.out.printf("Total: $%.2f\n",order.calculateTotalPrice());
        // System.out.println("-----------------------------------------------------------");

        // Display options
        System.out.println("Options:");
        System.out.println("1. Cancel order");
        System.out.println("2. Back");
        System.out.print("Enter your choice: ");
        int choice = 0;
        while (choice != 1 && choice != 2) {
            choice = getUserChoice();
        
            switch (choice) {
                case 1:
                    if (order.getShippingInfo().getShippingStatus() != ShippingStatus.PENDING) {
                        System.out.println(
                                "Cannot cancel order because it is " + order.getShippingInfo().getShippingStatus() + ".");
                    } else {
                        System.out.print("Are you sure you want to cancel this order? (yes/no): ");
                        String confirm = scanner.nextLine().trim().toLowerCase();
                        if (confirm.equals("yes")) {
                            orderService.cancelOrder(order);
                            System.out.println("Order cancelled successfully.");
                            scanner.nextLine();
                        } else {
                            System.out.println("Order cancellation cancelled.");
                            scanner.nextLine();
                        }
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    scanner.nextLine();
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

    public static void displayer(Order order) {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("Order Details:");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Order Date: " + dateFormat.format(order.getOrderDate()));
        System.out.println("Shipping Info:");
        System.out.println("  Method: " + order.getShippingInfo().getShippingMethod());
        System.out.println("  Cost: $" + order.getShippingInfo().getShippingCost());
        System.out.println("  Address: " + order.getShippingInfo().getShippingAddress());
        System.out.println("  Status: " + order.getShippingInfo().getShippingStatus());
        System.out.println("Payment Info:");
        System.out.println("  Method: " + order.getPaymentInfo().getPaymentMethod());
        System.out.println("  Status: " + order.getPaymentInfo().getPaymentStatus());
        System.out.println("Order Items:");
        System.out.println("  (Title)                (Price)    (Quantity)");
        for (Map.Entry<Book, Integer> entry : order.getOrderItems().entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("  %-22s $%-13.2f %-3d\n", book.getTitle(), book.getPrice(), quantity);
        }
        System.out.println("-----------------------------------------------------------");
        System.out.printf("Total: $%.2f\n",order.calculateTotalPrice());
        System.out.println("-----------------------------------------------------------");
    }
}
