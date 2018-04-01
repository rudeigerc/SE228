package bookstore.action.order;

import bookstore.action.BaseAction;
import bookstore.rmi.ExpressClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class GetStatusAction extends BaseAction {

    private int orderId;
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

    @Override
    public String execute() throws Exception {
        ExpressClient client = new ExpressClient();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String status = client.getStatus(Integer.toString(orderId));
            result.put("status", status);
        } catch (Exception e) {
            result.put("status", "Connection refused");
        }
;
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        json = gson.toJson(result);
        return SUCCESS;
    }



}
