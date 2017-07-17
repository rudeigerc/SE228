package bookstore.action.stat;

import bookstore.action.BaseAction;
import bookstore.service.AppService;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by rudeigerc on 2017/7/17.
 */
public class GetBookStatAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private String json;

    private AppService appService;

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
        List<Object> stat = appService.getBookStat();
        Gson gson = new Gson();
        json = gson.toJson(stat);
        return SUCCESS;
    }


}
