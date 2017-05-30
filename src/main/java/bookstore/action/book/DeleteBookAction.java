package bookstore.action.book;

import bookstore.action.BaseAction;
import bookstore.model.Book;
import bookstore.service.AppService;
/**
 * Created by rudeigerc on 2017/5/26.
 */
public class DeleteBookAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private String isbn;
    private AppService appService;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        Book book = appService.getBookByISBN(isbn);
        appService.deleteBook(book);
        return SUCCESS;
    }
}
