package bookstore.action.user;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.service.AppService;

import java.util.List;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public class ListUserAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {

        List<User> users = appService.getAllUsers();
        request().setAttribute("users", users);
        return SUCCESS;
    }
}
