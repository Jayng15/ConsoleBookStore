package loader;

import entity.Book;
import entity.Cart;
import entity.Customer;
import entity.Order;
import entity.Payment;
import entity.ShippingInfo;
import entity.enums.Payment.PaymentMethod;
import entity.enums.Payment.PaymentStatus;
import entity.enums.ShippingInfo.ShippingMethod;
import entity.enums.ShippingInfo.ShippingStatus;
import service.BookService;
import service.CustomerService;
import service.OrderService;
import service.adt.interfaces.List;

public class DataLoader {

    public static void fillBookService(BookService bookService) {
        // Add books to the book service
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 10.99,
                "Classic novel set in the Jazz Age.");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", 12.50,
                "Story of racial injustice and loss of innocence in the American South.");
        Book book3 = new Book("1984", "George Orwell", "9780451524935", 8.99,
                "Dystopian novel depicting totalitarian society.");
        Book book4 = new Book("Pride and Prejudice", "Jane Austen", "9780141439518", 7.99,
                "Romantic novel set in early 19th-century England.");
        Book book5 = new Book("The Catcher in the Rye", "J.D. Salinger", "9780316769488", 9.25,
                "Coming-of-age novel featuring protagonist Holden Caulfield.");

        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        bookService.addBook(book5);
        // Add more books as needed
        Book book6 = new Book("The Hobbit", "J.R.R. Tolkien", "9780547928227", 12.99,
                "Fantasy adventure novel about the quest of a hobbit named Bilbo Baggins.");
        Book book7 = new Book("The Alchemist", "Paulo Coelho", "9780062315007", 11.50,
                "Philosophical novel about a young shepherd named Santiago on a journey to find his personal legend.");
        Book book8 = new Book("The Hunger Games", "Suzanne Collins", "9780439023481", 8.79,
                "Dystopian novel set in a future where teenagers are forced to fight to the death in a televised event.");
        Book book11 = new Book("The Da Vinci Code", "Dan Brown", "9780307474278", 8.50,
                "Mystery thriller novel following symbologist Robert Langdon as he investigates a murder in the Louvre Museum and discovers a hidden secret.");

        bookService.addBook(book6);
        bookService.addBook(book7);
        bookService.addBook(book8);
        bookService.addBook(book11);
    }

    public static void fillCustomerService(CustomerService customerService) {
        Customer customer1 = new Customer(1, "John Doe", "john@example.com");
        Customer customer2 = new Customer(2, "Jane Smith", "jane@example.com");
        // Add customers to the customer service
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);
        // Add more customers as needed
    }

    public static void fillOrderService(BookService bookService, CustomerService customerService,
            OrderService orderService) {
        // Create sample shipping info
        ShippingInfo shippingInfo1 = new ShippingInfo(ShippingMethod.STANDARD, 5.99, "123 Street, City",
                ShippingStatus.DELIVERED);
        ShippingInfo shippingInfo2 = new ShippingInfo(ShippingMethod.EXPRESS, 9.99, "456 Avenue, Town",
                ShippingStatus.PENDING);
        ShippingInfo shippingInfo3 = new ShippingInfo(ShippingMethod.STANDARD, 7.99, "789 Road, Village",
                ShippingStatus.DELIVERED);

        // Create sample payment info
        Payment paymentInfo1 = new Payment(PaymentMethod.CREDIT_CARD, PaymentStatus.COMPLETED);
        Payment paymentInfo2 = new Payment(PaymentMethod.PAYPAL, PaymentStatus.PENDING);
        Payment paymentInfo3 = new Payment(PaymentMethod.CASH_ON_DELIVERY, PaymentStatus.COMPLETED);

        // Get sample books and customers
        Customer customer1 = customerService.getCustomerById(1);
        Customer customer2 = customerService.getCustomerById(2);
        Book book1 = bookService.getBooks().get(1);
        Book book2 = bookService.getBooks().get(2);
        Book book3 = bookService.getBooks().get(0);

        // Create sample carts and add books
        Cart cart1 = new Cart();
        cart1.addItem(book1, 2);
        cart1.addItem(book2, 1);

        Cart cart2 = new Cart();
        cart2.addItem(book2, 3);
        cart2.addItem(book3, 2);

        Cart cart3 = new Cart();
        cart3.addItem(book1, 1);
        cart3.addItem(book3, 2);

        // Create sample orders
        orderService.addOrder(customer1, cart1, shippingInfo1, paymentInfo1);
        orderService.addOrder(customer2, cart2, shippingInfo2, paymentInfo2);
        orderService.addOrder(customer1, cart3, shippingInfo3, paymentInfo3);

        // Add more orders as needed
    }

}
