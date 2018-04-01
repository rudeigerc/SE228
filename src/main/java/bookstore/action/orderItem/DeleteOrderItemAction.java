package bookstore.action.orderItem;

import bookstore.action.BaseAction;
import bookstore.model.OrderItem;
import bookstore.service.OrderItemService;
/**
 * Created by rudeigerc on 2017/5/28.
 */
public class DeleteOrderItemAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private int id;
    private OrderItemService orderItemService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Override
    public String execute() throws Exception {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        orderItemService.deleteOrderItem(orderItem);
        return SUCCESS;
    }

}
