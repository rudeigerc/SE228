package bookstore.action.book;

import bookstore.action.BaseAction;
import bookstore.model.Book;
import bookstore.service.BookService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rudeigerc on 2017/7/15.
 */
public class getBookDetailAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private String isbn;
    private String json;
    private BookService bookService;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute() throws Exception {
        Book book = bookService.getBookByISBN(isbn);
        Map<String, Object> _book = new HashMap<String, Object>();
        _book.put("book", book);
        Gson gson = new Gson();
        json = gson.toJson(_book);
        return SUCCESS;
    }


}
