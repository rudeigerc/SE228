package bookstore.action.user;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.model.UserInfo;
import bookstore.service.AppService;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public class DeleteUserAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private int uid;
    private AppService appService;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        User user = appService.getUserByUid(uid);
        UserInfo userInfo = appService.getUserInfoByUid(uid);
        appService.deleteUser(user);
        appService.deleteUserInfo(userInfo);
        return SUCCESS;
    }



}