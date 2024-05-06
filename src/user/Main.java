package user;

import entity.Book;
import entity.Customer;
import loader.DataLoader;
import service.BookService;
import service.CustomerService;
import service.OrderService;
import template.user.Menu;

public class Main {
    public static void main(String[] args) {
        // Create a BookService instance
        BookService bookService = new BookService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();

        DataLoader.fillBookService(bookService);
        DataLoader.fillCustomerService(customerService);
        DataLoader.fillOrderService(bookService, customerService, orderService);

        // Display the main menu
        Menu.menuDisplay(bookService, customerService.getCustomerById(1), orderService);
    }
}

