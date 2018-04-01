package bookstore.action.book;

import bookstore.action.BaseAction;
import bookstore.model.Book;
import bookstore.service.BookService;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/26.
 */
public class ListBookAction extends BaseAction{
    private static final long serialVersionUID = 1L;

    private BookService bookService;

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute() throws Exception {
        List<Book> books = bookService.getAllBooks();
        request().setAttribute("books", books);
        return SUCCESS;
    }

}
