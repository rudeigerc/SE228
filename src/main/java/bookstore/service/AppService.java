package bookstore.service;
import java.util.List;
import bookstore.model.User;
import bookstore.model.Book;
import bookstore.model.Order;
import bookstore.model.OrderItem;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public interface AppService {

    /* User */
    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserByUid(int uid);

    List<User> getAllUsers();

    List<String> getAllUsernames();

    /* Book */
    void addBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    Book getBookByISBN(String isbn);

    List<Book> getAllBooks();

    List<String> getAllIsbns();

    /* Order */
    void addOrder(Order order);

    void deleteOrder(Order order);

    void updateOrder(Order order);

    Order getOrderById(int orderId);

    List<Order> getAllOrders();

    List<Integer> getAllOrderIds();

    /* Order Item */

    void addOrderItem(OrderItem orderItem);

    void deleteOrderItem(OrderItem orderItem);

    void updateOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemByOrderId(int orderId);

    OrderItem getOrderItemById(int id);

    List<OrderItem> getAllOrderItems();



}
