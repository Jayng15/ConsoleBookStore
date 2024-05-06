package service;

import entity.Customer;
import service.adt.HashMapADT;
import service.adt.interfaces.Map;

public class CustomerService {
    private Map<Integer, Customer> customers;

    public CustomerService() {
        this.customers = new HashMapADT<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public Customer getCustomerById(int customerId) {
        return customers.get(customerId);
    }
}
