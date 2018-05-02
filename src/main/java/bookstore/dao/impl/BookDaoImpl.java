package bookstore.dao.impl;

import bookstore.dao.BookDao;
import bookstore.model.Book;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/26.
 */
public class BookDaoImpl extends HibernateDaoSupport implements BookDao {

    private Jedis jedis = new Jedis("localhost", 6379);

    public void save(Book book) {
        if (jedis.get("category") == null) {
            jedis.del("category");
        }
        getHibernateTemplate().save(book);
    }

    public void delete(Book book) {
        if (jedis.get("category") == null) {
            jedis.del("category");
        }
        getHibernateTemplate().delete(book);
    }

    public void update(Book book) {
        if (jedis.get("category") == null) {
            jedis.del("category");
        }
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
        List<Book> books = (List<Book>) getHibernateTemplate().find("from Book as book where book.category != 'Adult'");
        return books;
    }

    public List<String> getAllIsbns() {
        @SuppressWarnings("unchecked")
        List<String> isbns = (List<String>) getHibernateTemplate().find("select isbn from Book");
        return isbns;
    }

    @SuppressWarnings("unchecked")
    public List<String> getAllCategories() {
        List<String> categories = null;
        if (jedis.get("category") == null) {
            categories = (List<String>) getHibernateTemplate().find("select distinct category from Book");
            jedis.set("category", categories.toString());
        } else {
            String category = jedis.get("category");
            String[] array = category.substring(1, category.length() - 1).split(", ");
            categories = new ArrayList<>(Arrays.asList(array));
        }

        return categories;
    }

    public List<Object> getBookStat() {
        @SuppressWarnings("unchecked")
        List<Object> stat = (List<Object>) getHibernateTemplate().find("select o.username, o.time, b.title, b.category, oi.quantity, oi.price from Order as o join OrderItem as oi on o.orderId = oi.orderId join Book as b on oi.isbn = b.isbn");
        return stat;
    }
}
