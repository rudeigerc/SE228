package bookstore.action.order;

import bookstore.action.BaseAction;
import bookstore.model.Order;
import bookstore.service.AppService;


/**
 * Created by rudeigerc on 2017/5/28.
 */
public class UpdateOrderAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private int orderId;
    private String username;
    private String time;
    private String total;
    private int status;
    private AppService appService;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        Order order = appService.getOrderById(orderId);
        order.setOrderId(orderId);
        order.setUsername(username);
        order.setTime(time);
        order.setTotal(total);
        order.setStatus(status);
        appService.updateOrder(order);
        return SUCCESS;
    }
}
