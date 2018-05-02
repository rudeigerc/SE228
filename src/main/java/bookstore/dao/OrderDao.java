package bookstore.dao;

import bookstore.model.Order;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/28.
 */
public interface OrderDao {

    Integer save(Order order);

    void delete(Order order);

    void update(Order order);

    Order getOrderById(int orderId);

    List<Order> getAllOrders();

    List<Integer> getAllOrderIds();
}
