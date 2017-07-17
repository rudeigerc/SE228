package bookstore.service.impl;

import bookstore.dao.*;
import bookstore.model.*;
import bookstore.service.AppService;
import com.mysql.cj.jdbc.CallableStatement;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public class AppServiceImpl implements AppService {
    private UserDao userDao;
    private BookDao bookDao;
    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    private UserInfoDao userInfoDao;

    private DataSource dataSource;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setBookDao(BookDao bookDao) { this.bookDao = bookDao; }

    public void setOrderDao(OrderDao orderDao) { this.orderDao = orderDao; }

    public void setOrderItemDao(OrderItemDao orderItemDao) { this.orderItemDao = orderItemDao; }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    /* User */

    public Integer addUser(User user) {
        return userDao.save(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public User getUserByUid(int uid) {
        return userDao.getUserByUid(uid);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<String> getAllUsernames() {
        return userDao.getAllUsernames();
    }

    public void addUserInfo(UserInfo userInfo) {
        userInfoDao.addUserInfo(userInfo);
    }

    public void deleteUserInfo(UserInfo userInfo) {
        userInfoDao.deleteUserInfo(userInfo);
    }

    public void updateUserInfo(UserInfo userInfo) {
        userInfoDao.updateUserInfo(userInfo);
    }

    public UserInfo getUserInfoByUid(int uid) {
        return userInfoDao.getUserInfoByUid(uid);
    }

    public List<UserInfo> getAllUserInfos() {
        return userInfoDao.getAllUserInfos();
    }

    /* Book */

    public void addBook(Book book) {
        bookDao.save(book);
    }

    public void deleteBook(Book book) {
        bookDao.delete(book);
    }

    public void updateBook(Book book) {
        bookDao.update(book);
    }

    public Book getBookByISBN(String isbn) {
        return bookDao.getBookByISBN(isbn);
    }

    public List<Book> getBookByCategory(String category) {
        return bookDao.getBookByCategory(category);
    }

    public List<Book> getBookByKeyword(String keyword) {
        return bookDao.getBookByKeyword(keyword);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public List<String> getAllIsbns() {
        return bookDao.getAllIsbns();
    }

    public List<String> getAllCatagories() {
        return bookDao.getAllCategories();
    }

    public List<Object> getBookStat() {
        return bookDao.getBookStat();
    }

    /* Order */

    public Integer addOrder(Order order) {
        return orderDao.save(order);
    }

    public void deleteOrder(Order order) {
        orderDao.delete(order);
    }

    public void updateOrder(Order order) {
        orderDao.update(order);
    }

    public Order getOrderById(int orderId) {
        return orderDao.getOrderById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public List<Integer> getAllOrderIds() {
        return orderDao.getAllOrderIds();
    }

    /* Order Item */

    public void addOrderItem(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    public void deleteOrderItem(OrderItem orderItem) {
        orderItemDao.delete(orderItem);
    }

    public void updateOrderItem(OrderItem orderItem) {
        orderItemDao.update(orderItem);
    }

    public List<OrderItem> getOrderItemByOrderId(int orderId) {
        return orderItemDao.getOrderItemByOrderId(orderId);
    }

    public OrderItem getOrderItemById(int id) {
        return orderItemDao.getOrderItemById(id);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemDao.getAllOrderItems();
    }


}
