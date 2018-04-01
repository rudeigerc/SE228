package bookstore.action.stat;

import bookstore.action.BaseAction;
import bookstore.service.BookService;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by rudeigerc on 2017/7/17.
 */
public class GetBookStatAction extends BaseAction {
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
        List<Object> stat = bookService.getBookStat();
        Gson gson = new Gson();
        json = gson.toJson(stat);
        return SUCCESS;
    }

}
