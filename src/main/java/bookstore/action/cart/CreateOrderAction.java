package bookstore.action.cart;

import bookstore.action.BaseAction;
import bookstore.model.Book;
import bookstore.model.Order;
import bookstore.model.OrderItem;
import bookstore.service.AppService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rudeigerc on 2017/7/16.
 */
public class CreateOrderAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    private String json;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    private String total;
    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        Order order = new Order(username, time, total, 0);
        Integer orderId = appService.addOrder(order);

        Map<String, Integer> map = new HashMap<String, Integer>();
        HttpSession session = session();
        map = (Map<String, Integer>) session.getAttribute("cart");

        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();

        if (jsonArray == null) {
            return ERROR;
        }

        for (JsonElement jsonElement : jsonArray) {

            String isbn = jsonElement.getAsJsonObject().get("isbn").getAsString();
            String price = jsonElement.getAsJsonObject().get("price").getAsString();
            Integer amount = jsonElement.getAsJsonObject().get("amount").getAsInt();
            OrderItem orderItem = new OrderItem(orderId, isbn, amount, price);
            appService.addOrderItem(orderItem);

            Book book = appService.getBookByISBN(isbn);
            book.setInventory(book.getInventory() - amount);
            appService.updateBook(book);

            map.remove(isbn);
        }

        if (map.size() == 0) {
            session.removeAttribute("cart");
        } else {
            session.setAttribute("cart", map);
        }

        return SUCCESS;
    }
}
