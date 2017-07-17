package bookstore.dao.impl;

import bookstore.dao.BookDao;
import bookstore.model.Book;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
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

    public List<Book> getBookByKeyword(String keyword) {
        if (keyword.equals("")) {
            List<Book> _book = new ArrayList<Book>();
            return _book;
        }
        keyword = "%" + keyword + "%";
        String queryString = "from Book as book where book.title like ? or book.author like ? or book.category like ? or book.publisher like ? or book.isbn like ? or book.description like ?";
        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) getHibernateTemplate().find(queryString , keyword, keyword, keyword, keyword, keyword, keyword);
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

    public List<Object> getBookStat() {
        @SuppressWarnings("unchecked")
        List<Object> stat = (List<Object>) getHibernateTemplate().find("select o.username, o.time, b.title, b.category, oi.quantity, oi.price from Order as o join OrderItem as oi on o.orderId = oi.orderId join Book as b on oi.isbn = b.isbn");
        return stat;
    }
}
