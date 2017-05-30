package bookstore.action.orderItem;

import bookstore.action.BaseAction;
import bookstore.model.OrderItem;
import bookstore.service.AppService;
/**
 * Created by rudeigerc on 2017/5/28.
 */
public class DeleteOrderItemAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private int id;
    private AppService appService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        OrderItem orderItem = appService.getOrderItemById(id);
        appService.deleteOrderItem(orderItem);
        return SUCCESS;
    }
}
