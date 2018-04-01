package bookstore.action.user;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.model.UserInfo;
import bookstore.service.UserService;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public class DeleteUserAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private int uid;
    private UserService userService;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute() throws Exception {
        User user = userService.getUserByUid(uid);
        UserInfo userInfo = userService.getUserInfoByUid(uid);
        userService.deleteUser(user);
        userService.deleteUserInfo(userInfo);
        return SUCCESS;
    }


}