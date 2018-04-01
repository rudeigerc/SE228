package bookstore.action.cart;

import bookstore.action.BaseAction;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rudeigerc on 2017/7/16.
 */
public class DeleteFromCartAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String execute() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        HttpSession session = session();
        map = (Map<String, Integer>) session.getAttribute("cart");
        map.remove(isbn);
        if (map.size() == 0) {
            session.removeAttribute("cart");
        } else {
            session.setAttribute("cart", map);
        }
        return SUCCESS;
    }

}
