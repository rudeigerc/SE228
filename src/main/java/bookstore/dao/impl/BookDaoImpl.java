package bookstore.dao.impl;

import bookstore.dao.BookDao;
import bookstore.model.Book;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import java.util.List;
import java.util.Set;

/**
 * Created by rudeigerc on 2017/5/26.
 */
public class BookDaoImpl extends HibernateDaoSupport implements BookDao {
    public void save(Book book) {
        getHibernateTemplate().save(book);
    }

    public void delete(Book book) {
        getHibernateTemplate().delete(book);
    }

    public void update(Book book) {
        getHibernateTemplate().merge(book);
    }

    public Book getBookByISBN(String isbn) {
        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) getHibernateTemplate().find("from Book as book where book.isbn=?", isbn);
        Book book = books.size() > 0 ? books.get(0) : null;
        return book;
    }

    public List<Book> getBookByCategory(String category) {
        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) getHibernateTemplate().find("from Book as book where book.category=?", category);
        return books;
    }

    public List<Book> getAllBooks() {
        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) getHibernateTemplate().find("from Book");
        return books;
    }

    public List<String> getAllIsbns() {
        @SuppressWarnings("unchecked")
        List<String> isbns = (List<String>) getHibernateTemplate().find("select isbn from Book");
        return isbns;
    }

    public List<String> getAllCategories() {
        @SuppressWarnings("unchecked")
        List<String> categories = (List<String>) getHibernateTemplate().find("select distinct category from Book");
        return categories;
    }
}
