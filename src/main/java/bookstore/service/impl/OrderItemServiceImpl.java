package bookstore.service.impl;

import bookstore.dao.OrderItemDao;
import bookstore.model.OrderItem;
import bookstore.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemDao orderItemDao;

    public void setOrderItemDao(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

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
