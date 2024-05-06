package service;

import java.util.Scanner;

import entity.Book;
import entity.Cart;

public class CartService {
    public static void removeBook(Cart cart, int position) {
        int currentPosition = 0;
        Book bookToRemove = null;
        for (Book book : cart.getItems().keySet()) {
            if (currentPosition == position - 1) {
                bookToRemove = book;
                break;
            }
            currentPosition++;
        }
        if (bookToRemove != null) {
            cart.removeItem(bookToRemove);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Invalid position. No book found at the specified position.");
        }

    }

    public static void changeQuantity(Cart cart, int selectedIndex) {
        Scanner scanner = new Scanner(System.in);
        if (selectedIndex < 0 || selectedIndex >= cart.getItems().size()) {
            System.out.println("Invalid index. Please enter a valid index.");
            return;
        }

        Book selectedBook = cart.getItems().keySet().toArray(new Book[0])[selectedIndex];

        System.out.print("Enter the new quantity for the selected item: ");
        int newQuantity = Integer.parseInt(scanner.nextLine());

        cart.updateQuantity(selectedBook, newQuantity);

        System.out.println("Quantity updated successfully.");
    }
}
