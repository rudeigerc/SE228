package bookstore.action.order;

import bookstore.action.BaseAction;
import bookstore.model.Order;
import bookstore.service.AppService;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/28.
 */
public class ListOrderAction extends BaseAction {

    private static final long serialVersionUID = 1L;


    private AppService appService;


    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {

        List<Order> orders = appService.getAllOrders();
        List<String> usernames = appService.getAllUsernames();
        request().setAttribute("orders", orders);
        request().setAttribute("usernames", usernames);

        return SUCCESS;
    }
}
