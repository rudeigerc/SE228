package bookstore.action.orderItem;

import bookstore.action.BaseAction;
import bookstore.model.OrderItem;
import bookstore.service.OrderItemService;

/**
 * Created by rudeigerc on 2017/5/28.
 */
public class AddOrderItemAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private int id;
    private int orderId;
    private String isbn;
    private int quantity;
    private String price;

    private OrderItemService orderItemService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Override
    public String execute() throws Exception {
        OrderItem orderItem = new OrderItem(orderId, isbn, quantity, price);
        orderItemService.addOrderItem(orderItem);
        return SUCCESS;
    }

}
