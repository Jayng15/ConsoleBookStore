package entity;

import service.adt.QueueADT;

public class Customer {
    private String name;
    private String email;
    private String address;
    private QueueADT<Order> orders;
    private Cart cart;
    private int id;

    public Customer(int id, String name, String email) {
        this.name = name;
        this.email = email;
        // this.address = address;
        this.id = id;
        this.orders = new QueueADT<>();
        this.cart = new Cart();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public QueueADT<Order> getOrders() {
        return orders;
    }

    public void setOrders(QueueADT<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public int getCustomerId() {
        return this.id;
    }

    public void addOrder(Order order) {
        this.orders.enqueue(order);
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void removeOrder(Order order) {
        QueueADT<Order> tempQueue = new QueueADT<>();

        while (!orders.isEmpty()) {
            Order currentOrder = orders.dequeue();
            if (currentOrder.equals(order)) {
                order.getCustomer().removeOrder(order);
                continue;
            }
            tempQueue.enqueue(currentOrder);
        }
        orders = tempQueue;

    }
}
