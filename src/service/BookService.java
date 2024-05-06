package service;

import java.util.Comparator;
import java.util.Scanner;

import entity.Book;
import entity.Customer;
import service.adt.ArrayListADT;
import service.adt.interfaces.List;

import service.algorithm.LinearSearch;
import service.algorithm.MergeSort;
import service.algorithm.helper.BookValueExtractor;
import template.BooksDisplay;


public class BookService {
    private List<Book> books;

    private BookValueExtractor bookExtractor; 

    private LinearSearch<Book> binarySearch;

    public BookService() {
        this.books = new ArrayListADT<>();
        this.binarySearch = new LinearSearch<>();
        bookExtractor = new BookValueExtractor();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
    
    public List<Book> getBooks() {
        return books;
    }


    public void findBookByTitle(String title, Customer customer) {
        Scanner scanner = new Scanner(System.in);
        List<Book> foundedBooks = binarySearch.search(books, "title", title, bookExtractor);
        if (foundedBooks != null) {
            MergeSort.sort(foundedBooks, Comparator.comparing(Book::getPrice));
            BooksDisplay.displayBooks(foundedBooks, customer);
        } else {
            System.out.print(title + " cannot be found");
            scanner.nextLine();
        }
    }

    public void findBooksByAuthor(String author, Customer customer) {
        Scanner scanner = new Scanner(System.in);
        List<Book> foundedBooks = binarySearch.search(books, "author", author, bookExtractor);
        if (foundedBooks != null) {
            MergeSort.sort(foundedBooks, Comparator.comparing(Book::getPrice));
            BooksDisplay.displayBooks(foundedBooks, customer);
        } else {
            System.out.print(author + " cannot be found");
            scanner.nextLine();
        }
    }

    public void findBooksByISBN(String isbn, Customer customer) {
        Scanner scanner = new Scanner(System.in);
        List<Book> foundedBooks = binarySearch.search(books, "isbn", isbn, bookExtractor);
        if (foundedBooks != null) {
            MergeSort.sort(foundedBooks, Comparator.comparing(Book::getPrice));
            BooksDisplay.displayBooks(foundedBooks, customer);
        } else {
            System.out.print(isbn + " cannot be found");
            scanner.nextLine();
        }
    }
}

