package bookstore.action.book;

import bookstore.action.BaseAction;
import bookstore.model.Book;
import bookstore.service.BookService;

import java.sql.Date;

/**
 * Created by rudeigerc on 2017/5/26.
 */
public class AddBookAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String publishedDate;
    private String category;
    private String price;
    private int inventory;
    private String description;

    private BookService bookService;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute() throws Exception {
        Book book = new Book(title, author, isbn, publisher, publishedDate, category, price, description, inventory);
        bookService.addBook(book);
        return SUCCESS;
    }


}
