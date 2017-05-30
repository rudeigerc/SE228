package bookstore.dao;
import java.util.List;
import bookstore.model.Book;

/**
 * Created by rudeigerc on 2017/5/26.
 */
public interface BookDao {
    void save(Book book);
    void delete(Book book);
    void update(Book book);
    Book getBookByISBN(String isbn);
    List<Book> getAllBooks();
    List<String> getAllIsbns();
}