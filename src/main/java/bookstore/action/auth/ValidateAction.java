package bookstore.action.auth;

import bookstore.action.BaseAction;
import bookstore.service.AppService;

/**
 * Created by rudeigerc on 2017/7/17.
 */
public class ValidateAction extends BaseAction {

    private String username;

    private AppService appService;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        if (appService.getUserByUsername(username) == null) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }


}
