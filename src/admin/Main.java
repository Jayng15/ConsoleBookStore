package admin;

import entity.*;
import loader.DataLoader;
import service.BookService;
import service.CustomerService;
import service.OrderService;
import template.admin.Menu;

public class Main {
    public static void main(String[] args) throws Exception {
        BookService bookService = new BookService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();

        DataLoader.fillBookService(bookService);
        DataLoader.fillCustomerService(customerService);
        DataLoader.fillOrderService(bookService, customerService, orderService);
        Menu.displayAdminMenu(orderService, customerService, bookService);
    }
}
