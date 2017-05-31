package bookstore.action;

import bookstore.model.Book;
import bookstore.service.AppService;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/27.
 */
public class IndexAction extends BaseAction{
    private static final long serialVersionUID = 1L;

    String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        List<Book> books;
        if (category != null) {
            books = appService.getBookByCategory(category);
        }
        else {
            books = appService.getAllBooks();
        }
        List<String> categories = appService.getAllCatagories();
        request().setAttribute("books", books);
        request().setAttribute("categories", categories);
        return SUCCESS;
    }
}
