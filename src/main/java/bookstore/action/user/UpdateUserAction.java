package bookstore.action.user;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.service.UserService;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public class UpdateUserAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private int uid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String role;

    private UserService userService;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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
        this.role = role;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute() throws Exception {
        User user = userService.getUserByUid(uid);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        user.setRole(role);
        userService.updateUser(user);
        return SUCCESS;
    }


}
