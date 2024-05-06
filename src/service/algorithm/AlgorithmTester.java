package service.algorithm;

import loader.DataLoader;
import service.BookService;
import service.CustomerService;
import service.OrderService;
import service.adt.interfaces.List;
import service.algorithm.helper.BookValueExtractor;
import template.BookDisplay;
import template.BooksDisplay;

import java.util.Comparator;

import entity.*;

public class AlgorithmTester {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();

        DataLoader.fillBookService(bookService);
        DataLoader.fillCustomerService(customerService);
        DataLoader.fillOrderService(bookService, customerService, orderService);

        BooksDisplay.displayer(bookService.getBooks());
        long startTime = System.currentTimeMillis();

        MergeSort.sort(bookService.getBooks(), Comparator.comparing(Book::getPrice));

        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;

        System.out.println("Merge sort completed in " + elapsedTime + " milliseconds.");
        BooksDisplay.displayer(bookService.getBooks());
    }    
}
