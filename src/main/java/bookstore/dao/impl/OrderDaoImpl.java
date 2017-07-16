package bookstore.dao.impl;

import bookstore.dao.OrderDao;
import bookstore.model.Order;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/28.
 */
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {
    public Integer save(Order order) {
        Integer orderId = (Integer)getHibernateTemplate().save(order);
        return orderId;
    }

    public void delete(Order order) {
        getHibernateTemplate().delete(order);
    }

    public void update(Order order) {
        getHibernateTemplate().merge(order);
    }

    public Order getOrderById(int orderId) {
        @SuppressWarnings("unchecked")
        List<Order> orders = (List<Order>) getHibernateTemplate().find("from Order as order where order.orderId=?", orderId);
        Order order = orders.size() > 0 ? orders.get(0) : null;
        return order;
    }

    public List<Order> getAllOrders() {
        @SuppressWarnings("unchecked")
        List<Order> orders = (List<Order>) getHibernateTemplate().find("from Order");
        return orders;
    }

    public List<Integer> getAllOrderIds() {
        @SuppressWarnings("unchecked")
        List<Integer> orderIds = (List<Integer>) getHibernateTemplate().find("select orderId from Order");
        return orderIds;
    }
}
