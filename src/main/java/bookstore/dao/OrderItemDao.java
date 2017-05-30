package bookstore.dao;
import bookstore.model.OrderItem;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/28.
 */
public interface OrderItemDao {
    void save(OrderItem orderItem);
    void delete(OrderItem orderItem);
    void update(OrderItem orderItem);
    OrderItem getOrderItemById(int id);
    List<OrderItem> getOrderItemByOrderId(int orderId);
    List<OrderItem> getAllOrderItems();
}
