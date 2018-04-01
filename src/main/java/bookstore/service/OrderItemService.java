package bookstore.service;

import bookstore.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    void addOrderItem(OrderItem orderItem);

    void deleteOrderItem(OrderItem orderItem);

    void updateOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemByOrderId(int orderId);

    OrderItem getOrderItemById(int id);

    List<OrderItem> getAllOrderItems();
}
