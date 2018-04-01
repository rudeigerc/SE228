package bookstore.action.auth;

import bookstore.action.BaseAction;
import bookstore.service.UserService;

/**
 * Created by rudeigerc on 2017/7/17.
 */
public class ValidateAction extends BaseAction {

    private String username;

    private UserService userService;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute() throws Exception {
        if (userService.getUserByUsername(username) == null) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

}
