package bookstore.action.order;

import bookstore.action.BaseAction;
import bookstore.model.Order;
import bookstore.service.OrderService;

/**
 * Created by rudeigerc on 2017/5/28.
 */
public class DeleteOrderAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private int orderId;
    private OrderService orderService;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute() throws Exception {
        Order order = orderService.getOrderById(orderId);
        orderService.deleteOrder(order);
        return SUCCESS;
    }

}
