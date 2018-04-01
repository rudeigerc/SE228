package bookstore.action.order;

import bookstore.action.BaseAction;
import bookstore.model.Order;
import bookstore.service.OrderService;

/**
 * Created by rudeigerc on 2017/5/28.
 */
public class AddOrderAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private int orderId;
    private String username;
    private String time;
    private String total;
    private int status;
    private OrderService orderService;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute() throws Exception {
        Order order = new Order(username, time, total, 0);
        orderService.addOrder(order);
        return SUCCESS;
    }

}
