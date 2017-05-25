package bookstore.action.user;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.service.AppService;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public class AddUserAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String phone;
    private String email;
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if ("".equals(role)) role = "ROLE_USER";
        this.role = role;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        User user = new User(username, password, phone, email, role);
        appService.addUser(user);
        return SUCCESS;
    }
}
