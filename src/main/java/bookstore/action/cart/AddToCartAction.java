package bookstore.action.cart;

import bookstore.action.BaseAction;
import bookstore.service.AppService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rudeigerc on 2017/7/16.
 */
public class AddToCartAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private String isbn;
    private Integer amount;
    private AppService appService;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {

        Map<String, Integer> map = new HashMap<String, Integer>();
        HttpSession session = session();
        if (session.getAttribute("cart") != null) {
            map = (Map<String, Integer>) session.getAttribute("cart");
        }
        if (map.get(isbn) == null) {
            map.put(isbn, amount);
        } else {
            map.put(isbn, map.get(isbn) + amount);
        }
        session.setAttribute("cart", map);
        return SUCCESS;
    }
}
