package bookstore.service;

import bookstore.model.Order;

import java.util.List;

public interface OrderService {

    Integer addOrder(Order order);

    void deleteOrder(Order order);

    void updateOrder(Order order);

    Order getOrderById(int orderId);

    List<Order> getAllOrders();

    List<Integer> getAllOrderIds();
}
