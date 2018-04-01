package bookstore.action.userInfo;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.model.UserInfo;
import bookstore.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by rudeigerc on 2017/7/17.
 */
public class UpdateUserInfoAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private String phone;
    private String email;
    private String name;
    private String address;
    private int age;

    private UserService userService;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String execute() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.getUserByUsername(username);
        int uid = user.getUid();
        UserInfo userInfo = userService.getUserInfoByUid(uid);

        user.setEmail(email);
        user.setPhone(phone);
        userInfo.setName(name);
        userInfo.setAddress(address);
        userInfo.setAge(age);

        userService.updateUserInfo(userInfo);
        userService.updateUser(user);

        return SUCCESS;
    }


}
