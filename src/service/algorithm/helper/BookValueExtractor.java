package service.algorithm.helper;

import entity.Book;

public class BookValueExtractor implements ValueExtractor<Book> {

    @Override
    public String extractValue(Book book, String searchCriteria) {
        switch (searchCriteria) {
            case "isbn":
                return book.getIsbn(); 
            case "author":
                return book.getAuthor();
            case "title":
                return book.getTitle();
            default:
                return "";
        }
    }
}

