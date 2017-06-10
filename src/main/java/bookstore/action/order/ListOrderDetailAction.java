package bookstore.action.order;

import bookstore.action.BaseAction;
import bookstore.model.OrderItem;
import bookstore.service.AppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
/**
 * Created by rudeigerc on 2017/5/29.
 */
public class ListOrderDetailAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private int orderId;
    private AppService appService;
    private String json;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }


    @Override
    public String execute() throws Exception {
        List<OrderItem> data = appService.getOrderItemByOrderId(orderId);
        Map<String, Object> orderItem = new HashMap<String, Object>();
        orderItem.put("data", data);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        json = gson.toJson(orderItem);
        return SUCCESS;
    }
}
