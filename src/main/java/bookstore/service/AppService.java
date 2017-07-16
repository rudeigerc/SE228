package bookstore.service;
import java.util.List;

import bookstore.model.*;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public interface AppService {

    /* User */
    Integer addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserByUid(int uid);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    List<String> getAllUsernames();

    void addUserInfo(UserInfo userInfo);

    void deleteUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);

    UserInfo getUserInfoByUid(int uid);

    List<UserInfo> getAllUserInfos();

    /* Book */
    void addBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    Book getBookByISBN(String isbn);

    List<Book> getBookByCategory(String category);

    List<Book> getBookByKeyword(String keyword);

    List<Book> getAllBooks();

    List<String> getAllIsbns();

    List<String> getAllCatagories();

    /* Order */
    Integer addOrder(Order order);

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
