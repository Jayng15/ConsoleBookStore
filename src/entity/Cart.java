package entity;

import service.adt.HashMapADT;
import service.adt.interfaces.Map;

public class Cart {
    private Map<Book, Integer> items;

    public Cart() {
        this.items = new HashMapADT<>();
    }

    public void addItem(Book book, int quantity) {
        if (items.containsKey(book)) {
            int currentQuantity = items.get(book);
            items.put(book, currentQuantity + quantity);
        } else {
            items.put(book, quantity);
        }
    }

    public void removeItem(Book book) {
        items.remove(book);
    }

    public void updateQuantity(Book book, int quantity) {
        if (items.containsKey(book)) {
            items.put(book, quantity);
        }
    }

    public Map<Book, Integer> getItems() {
        return this.items;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Book, Integer> entry : items.entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += book.getPrice() * quantity;
        }
        return totalPrice;
    }

    public void clear() {
        this.items = new HashMapADT<Book, Integer>();
    }

    public void addItems(Map<Book, Integer> items) {
        this.items = items;
    }
}

