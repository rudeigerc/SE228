package bookstore.action.book;

import bookstore.action.BaseAction;
import bookstore.model.Book;
import bookstore.service.AppService;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/26.
 */
public class ListBookAction extends BaseAction{
    private static final long serialVersionUID = 1L;

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {

        List<Book> books = appService.getAllBooks();
        request().setAttribute("books", books);
        return SUCCESS;
    }
}
