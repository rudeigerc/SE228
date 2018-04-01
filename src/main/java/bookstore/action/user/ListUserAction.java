package bookstore.action.user;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.service.UserService;

import java.util.List;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public class ListUserAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute() throws Exception {

        List<User> users = userService.getAllUsers();
        request().setAttribute("users", users);
        return SUCCESS;
    }

}
