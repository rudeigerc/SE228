package bookstore.action;

import bookstore.model.Book;
import bookstore.service.AppService;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/27.
 */
public class showBookAtIndex extends BaseAction{
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
