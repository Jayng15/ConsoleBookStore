package entity;

import java.util.Scanner;

import entity.enums.ShippingInfo.ShippingMethod;
import entity.enums.ShippingInfo.ShippingStatus;

public class ShippingInfo {
    // private int shippingId;
    private ShippingMethod shippingMethod;
    private double shippingCost;
    private String shippingAddress;
    private ShippingStatus shippingStatus;

    // Constructor
    public ShippingInfo(ShippingMethod shippingMethod, double shippingCost, String shippingAddress,
            ShippingStatus status) {
        this.shippingMethod = shippingMethod;
        this.shippingCost = shippingCost;
        this.shippingAddress = shippingAddress;
        this.shippingStatus = status; // Initialize the status
    }

    public static ShippingInfo promptShippingInfo(Scanner scanner) {

        System.out.println("1. Standard");
        System.out.println("2. Express");
        System.out.print("Enter shipping method: ");

        ShippingMethod shippingMethod = null;
        boolean validInput = false;

        while (!validInput) {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shippingMethod = ShippingMethod.STANDARD;
                    validInput = true;
                    break;
                case 2:
                    shippingMethod = ShippingMethod.EXPRESS;
                    validInput = true;
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
                    break;
            }
        }

        // Calculate shipping cost based on shipping method
        double shippingCost = calculateShippingCost(shippingMethod);

        System.out.print("Enter shipping address: ");
        String shippingAddress = scanner.nextLine();

        ShippingStatus shippingStatus = ShippingStatus.PENDING;

        // scanner.close();

        return new ShippingInfo(shippingMethod, shippingCost, shippingAddress, shippingStatus);
    }

    private static double calculateShippingCost(ShippingMethod shippingMethod) {
        // Calculate shipping cost based on the chosen method
        switch (shippingMethod) {
            case STANDARD:
                return 10.0; // Example cost for standard shipping
            case EXPRESS:
                return 20.0; // Example cost for express shipping
            default:
                return 0.0; // Default cost if method is not recognized
        }
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getStatus() {
        return shippingStatus.toString();
    }

}
