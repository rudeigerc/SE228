package bookstore.dao;
import java.util.List;
import java.util.Set;

import bookstore.model.Book;

/**
 * Created by rudeigerc on 2017/5/26.
 */
public interface BookDao {
    void save(Book book);
    void delete(Book book);
    void update(Book book);
    Book getBookByISBN(String isbn);
    List<Book> getBookByCategory(String category);
    List<Book> getBookByKeyword(String keyword);
    List<Book> getAllBooks();
    List<String> getAllIsbns();
    List<String> getAllCategories();
}