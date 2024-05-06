package entity.relationship;

import entity.*;

public class OrderItem {
    private Book book;
    private int quantity;
    // private Order order;

    public OrderItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        // this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

