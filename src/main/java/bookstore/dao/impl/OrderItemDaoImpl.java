package bookstore.dao.impl;

import bookstore.dao.OrderItemDao;
import bookstore.model.OrderItem;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/28.
 */
public class OrderItemDaoImpl extends HibernateDaoSupport implements OrderItemDao {
    public void save(OrderItem orderItem) {
        getHibernateTemplate().save(orderItem);
    }

    public void delete(OrderItem orderItem) {
        getHibernateTemplate().delete(orderItem);
    }

    public void update(OrderItem orderItem) {
        getHibernateTemplate().merge(orderItem);
    }

    public OrderItem getOrderItemById(int id) {
        @SuppressWarnings("unchecked")
        List<OrderItem> orders = (List<OrderItem>) getHibernateTemplate().find("from OrderItem as orderItem where orderItem.id=?", id);
        OrderItem orderItem = orders.size() > 0 ? orders.get(0) : null;
        return orderItem;
    }

    public List<OrderItem> getOrderItemByOrderId(int orderId) {
        @SuppressWarnings("unchecked")
        List<OrderItem> orderItems = (List<OrderItem>) getHibernateTemplate().find("from OrderItem as orderItem where orderItem.orderId=?", orderId);
        return orderItems;
    }

    public List<OrderItem> getAllOrderItems() {
        @SuppressWarnings("unchecked")
        List<OrderItem> orderItems = (List<OrderItem>) getHibernateTemplate().find("from OrderItem ");
        return orderItems;
    }
}
