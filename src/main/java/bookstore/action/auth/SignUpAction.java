package bookstore.action.auth;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.model.UserInfo;
import bookstore.service.AppService;

/**
 * Created by rudeigerc on 2017/5/30.
 */
public class SignUpAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String phone;
    private String email;

    private AppService appService;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        User user = new User(username, password, phone, email, "ROLE_USER");
        Integer uid = appService.addUser(user);
        UserInfo userinfo = new UserInfo(uid, "", username, "");
        appService.addUserInfo(userinfo);
        return SUCCESS;

    }
}
