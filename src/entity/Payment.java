package entity;

import java.util.NoSuchElementException;
import java.util.Scanner;

import entity.enums.Payment.PaymentMethod;
import entity.enums.Payment.PaymentStatus;

public class Payment {
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    // Constructor
    public Payment(PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    // public int getPaymentId() {
    // return paymentId;
    // }

    // public void setPaymentId(int paymentId) {
    // this.paymentId = paymentId;
    // }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public static Payment promptPaymentInfo(Scanner scanner) {

        PaymentMethod paymentMethod = null;
        PaymentStatus paymentStatus = PaymentStatus.PENDING;
        PaymentMethod[] methods = PaymentMethod.values();

        for (int i = 0; i < methods.length; i++) {
            System.out.println((i + 1) + ". " + methods[i]);
        }
        System.out.print("Enter payment method: ");
        Boolean validInput = false;
        while (validInput != true) {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    paymentMethod = PaymentMethod.CREDIT_CARD;
                    validInput = true;
                    break;
                case 2:
                    paymentMethod = PaymentMethod.PAYPAL;
                    validInput = true;
                    break;
                case 3:
                    paymentMethod = PaymentMethod.BANK_TRANSFER;
                    validInput = true;
                    break;
                case 4:
                    paymentMethod = PaymentMethod.CASH_ON_DELIVERY;
                    validInput = true;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

        return new Payment(paymentMethod, paymentStatus);
    }

}
