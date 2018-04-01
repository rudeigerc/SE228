package bookstore.action.book;

import bookstore.action.BaseAction;
import bookstore.model.Book;
import bookstore.service.BookService;

/**
 * Created by rudeigerc on 2017/5/26.
 */
public class DeleteBookAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private String isbn;
    private BookService bookService;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute() throws Exception {
        Book book = bookService.getBookByISBN(isbn);
        bookService.deleteBook(book);
        return SUCCESS;
    }


}
