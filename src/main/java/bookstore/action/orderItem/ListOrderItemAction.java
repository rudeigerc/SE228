package bookstore.action.orderItem;

import bookstore.action.BaseAction;
import bookstore.model.OrderItem;
import bookstore.service.BookService;
import bookstore.service.OrderItemService;
import bookstore.service.OrderService;

import java.util.List;
/**
 * Created by rudeigerc on 2017/5/28.
 */
public class ListOrderItemAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private BookService bookService;
    private OrderService orderService;
    private OrderItemService orderItemService;


    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Override
    public String execute() throws Exception {

        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        List<String> isbns = bookService.getAllIsbns();
        List<Integer> orderIds = orderService.getAllOrderIds();
        request().setAttribute("orderItems", orderItems);
        request().setAttribute("isbns", isbns);
        request().setAttribute("orderIds", orderIds);
        return SUCCESS;
    }

}
