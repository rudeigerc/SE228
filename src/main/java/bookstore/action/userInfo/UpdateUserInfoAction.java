package bookstore.action.userInfo;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.model.UserInfo;
import bookstore.service.AppService;
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

    private AppService appService;

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

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public String execute() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = appService.getUserByUsername(username);
        int uid = user.getUid();
        UserInfo userInfo = appService.getUserInfoByUid(uid);

        user.setEmail(email);
        user.setPhone(phone);
        userInfo.setName(name);
        userInfo.setAddress(address);

        appService.updateUserInfo(userInfo);
        appService.updateUser(user);

        return SUCCESS;
    }


}
