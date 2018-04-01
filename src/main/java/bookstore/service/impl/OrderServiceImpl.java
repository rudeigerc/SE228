package bookstore.service.impl;

import bookstore.dao.OrderDao;
import bookstore.model.Order;
import bookstore.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

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


}
