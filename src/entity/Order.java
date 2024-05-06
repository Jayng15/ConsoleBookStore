package entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import service.adt.interfaces.List;
// import entity.relationship.OrderItem;
import service.adt.interfaces.Map;
import service.algorithm.MergeSort;
import service.adt.*;

public class Order {

    private int orderId;
    private Customer customer;
    private Date orderDate;
    private ShippingInfo shippingInfo;
    private Payment paymentInfo;
    private Map<Book, Integer> orderItems;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = new Date();
        this.orderItems = new HashMapADT<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public Payment getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(Payment paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Map<Book, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItem(Map<Book, Integer> orderItems) {
        List<Book> bookList = new ArrayListADT<>();
        for (Map.Entry<Book, Integer> entry : orderItems.entrySet()) {
            bookList.add(entry.getKey());
        }
        MergeSort.sort(bookList, Comparator.comparing(Book::getPrice));

        Map<Book, Integer> sortedOrderItems = new HashMapADT<>();

        for (int i = 0; i < bookList.size(); i++) {
            Integer quantity = orderItems.get(bookList.get(i));
            sortedOrderItems.put(bookList.get(i), quantity);
        }
        this.orderItems = sortedOrderItems;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Book, Integer> entry : orderItems.entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += quantity * book.getPrice();
        }
        totalPrice += shippingInfo.getShippingCost();
        return totalPrice;
    }

    public String getStatus() {
        if (shippingInfo != null) {
            return shippingInfo.getStatus();
        } else {
            return "Unknown";
        }
    }

}
