package service;

import java.util.Comparator;
// import java.util.List;

import entity.Cart;
import entity.Customer;
import entity.Order;
import entity.Payment;
import entity.ShippingInfo;
import service.adt.ArrayListADT;
import service.adt.QueueADT;
import service.adt.interfaces.List;
import service.algorithm.MergeSort;

public class OrderService {
    private QueueADT<Order> orderQueue;
    private int size;

    public OrderService() {
        this.orderQueue = new QueueADT<>();
        size = 1;
    }

    public void addOrder(Customer customer, Cart cart, ShippingInfo shippingInfo, Payment paymentInfo) {
        Order newOrder = new Order(size++, customer);
        newOrder.setOrderItem(cart.getItems());
        newOrder.setShippingInfo(shippingInfo);
        newOrder.setPaymentInfo(paymentInfo);
        orderQueue.enqueue(newOrder);
        customer.addOrder(newOrder);
        cart.clear();
        // sortOrders();
    }


    public QueueADT<Order> getOrders() {
        return this.orderQueue;
    }

    public Order processNextOrder() {

        if (orderQueue.isEmpty()) {
            System.out.println("No orders in the queue.");
            return null;
        }
        return orderQueue.dequeue();
    }

    public Order peekNextOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders in the queue.");
            return null;
        }
        return orderQueue.peek();
    }

    public boolean isQueueEmpty() {
        return orderQueue.isEmpty();
    }

    public List<Order> sortOrdersByPrice(List<Order> orderList) {
        MergeSort.sort(orderList, Comparator.comparing(Order::calculateTotalPrice));
        return orderList;
    }
    
    public void cancelOrder(Order order) {
    QueueADT<Order> tempQueue = new QueueADT<>();

    while (!orderQueue.isEmpty()) {
        Order currentOrder = orderQueue.dequeue();
        if (currentOrder.equals(order)) {
            order.getCustomer().removeOrder(order);
            continue;
        }
        tempQueue.enqueue(currentOrder);
    }
        orderQueue = tempQueue;
    }
}


