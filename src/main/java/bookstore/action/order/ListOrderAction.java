package bookstore.action.order;

import bookstore.action.BaseAction;
import bookstore.model.Order;
import bookstore.service.OrderService;
import bookstore.service.UserService;

import java.util.List;

/**
 * Created by rudeigerc on 2017/5/28.
 */
public class ListOrderAction extends BaseAction {

    private static final long serialVersionUID = 1L;


    private OrderService orderService;

    private UserService userService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute() throws Exception {

        List<Order> orders = orderService.getAllOrders();
        List<String> usernames = userService.getAllUsernames();
        request().setAttribute("orders", orders);
        request().setAttribute("usernames", usernames);

        return SUCCESS;
    }

}
