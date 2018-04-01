package bookstore.service;

import bookstore.model.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    Book getBookByISBN(String isbn);

    List<Book> getBookByCategory(String category);

    List<Book> getBookByKeyword(String keyword);

    List<Book> getAllBooks();

    List<String> getAllIsbns();

    List<String> getAllCatagories();

    List<Object> getBookStat();
}
