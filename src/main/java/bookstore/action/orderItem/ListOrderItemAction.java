package bookstore.action.orderItem;

import bookstore.action.BaseAction;
import bookstore.model.OrderItem;
import bookstore.service.AppService;
import java.util.List;
/**
 * Created by rudeigerc on 2017/5/28.
 */
public class ListOrderItemAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {

        List<OrderItem> orderItems = appService.getAllOrderItems();
        List<String> isbns = appService.getAllIsbns();
        List<Integer> orderIds = appService.getAllOrderIds();
        request().setAttribute("orderItems", orderItems);
        request().setAttribute("isbns", isbns);
        request().setAttribute("orderIds", orderIds);
        return SUCCESS;
    }

}
