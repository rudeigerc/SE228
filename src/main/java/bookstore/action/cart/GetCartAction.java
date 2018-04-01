package bookstore.action.cart;

import bookstore.action.BaseAction;
import bookstore.model.Book;
import bookstore.service.BookService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rudeigerc on 2017/7/16.
 */
public class GetCartAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private String json;

    private BookService bookService;

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
        HttpSession session = session();
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<String> list = new ArrayList<String>();
        Gson gson = new Gson();
        if (session.getAttribute("cart") != null) {
            map = (Map<String, Integer>) session.getAttribute("cart");
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String isbn = entry.getKey();
                Book book = bookService.getBookByISBN(isbn);
                String _book = gson.toJson(book);
                _book = _book.substring(0, _book.length() - 1);
                Integer amount = entry.getValue();
                _book = _book + ", \"amount\": " + amount + "}";
                list.add(_book);
            }
        }
        json = gson.toJson(list);
        return SUCCESS;
    }


}
