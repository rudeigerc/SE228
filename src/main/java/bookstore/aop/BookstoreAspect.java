package bookstore.aop;

import bookstore.model.Order;
import java.util.logging.Logger;

public class BookstoreAspect {

    private final Logger logger = Logger.getLogger("bookstore");

    public void beforeDeleteOrder() {
        logger.info("Before deleting order.");
    }

    public void afterDeleteOrder(Order order) {
        logger.info(String.format("Delete Order: #%d.", order.getOrderId()));
    }

}
