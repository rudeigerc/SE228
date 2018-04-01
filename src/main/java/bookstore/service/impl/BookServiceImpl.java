package bookstore.service.impl;

import bookstore.dao.BookDao;
import bookstore.model.Book;
import bookstore.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void addBook(Book book) {
        bookDao.save(book);
    }

    public void deleteBook(Book book) {
        bookDao.delete(book);
    }

    public void updateBook(Book book) {
        bookDao.update(book);
    }

    public Book getBookByISBN(String isbn) {
        return bookDao.getBookByISBN(isbn);
    }

    public List<Book> getBookByCategory(String category) {
        return bookDao.getBookByCategory(category);
    }

    public List<Book> getBookByKeyword(String keyword) {
        return bookDao.getBookByKeyword(keyword);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public List<String> getAllIsbns() {
        return bookDao.getAllIsbns();
    }

    public List<String> getAllCatagories() {
        return bookDao.getAllCategories();
    }

    public List<Object> getBookStat() {
        return bookDao.getBookStat();
    }


}
