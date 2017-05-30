package bookstore.action.order;

import bookstore.action.BaseAction;
import bookstore.model.Order;
import bookstore.service.AppService;

/**
 * Created by rudeigerc on 2017/5/28.
 */
public class DeleteOrderAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private int orderId;
    private AppService appService;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        Order order = appService.getOrderById(orderId);
        appService.deleteOrder(order);
        return SUCCESS;
    }
}
