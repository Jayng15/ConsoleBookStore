package service.algorithm.helper;

import entity.Order;

public class OrderValueExtractor implements ValueExtractor<Order> {

    @Override
    public String extractValue(Order order, String searchCriteria) {
        switch (searchCriteria) {
            case "customerName":
                return order.getCustomer().getName();
            default:
                return "";
        }
    }
}

