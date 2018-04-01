package bookstore.action.cart;

import bookstore.action.BaseAction;
import bookstore.auth.RSA;
import bookstore.kafka.OrderProducer;
import bookstore.model.Book;
import bookstore.model.Order;
import bookstore.model.OrderItem;
import bookstore.service.BookService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private String billing;

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }

    private String total;
    private BookService bookService;

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute() throws Exception {

        if (total.equals("0")) {
            return ERROR;
        }

        String payment = new String(RSA.decrypt(billing));
        System.out.println(payment);

        OrderProducer producer = new OrderProducer();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        Order order = new Order(username, time, total, 0);
        // Integer orderId = appService.addOrder(order);

        Map<String, Integer> map = new HashMap<String, Integer>();
        HttpSession session = session();

        map = (Map<String, Integer>) session.getAttribute("cart");

        List<OrderItem> orderItems = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();

        for (JsonElement jsonElement : jsonArray) {

            String isbn = jsonElement.getAsJsonObject().get("isbn").getAsString();
            String price = jsonElement.getAsJsonObject().get("price").getAsString();
            Integer amount = jsonElement.getAsJsonObject().get("amount").getAsInt();
            OrderItem orderItem = new OrderItem(-1, isbn, amount, price);
            orderItems.add(orderItem);
            //appService.addOrderItem(orderItem);

            Book book = bookService.getBookByISBN(isbn);
            book.setInventory(book.getInventory() - amount);
            books.add(book);
            //appService.updateBook(book);

            map.remove(isbn);
        }

        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<>();
        data.put("order", gson.toJson(order));
        data.put("orderItems", gson.toJson(orderItems));
        data.put("books", gson.toJson(books));
        producer.produce(gson.toJson(data));

        if (map.size() == 0) {
            session.removeAttribute("cart");
        } else {
            session.setAttribute("cart", map);
        }

        return SUCCESS;
    }


}
