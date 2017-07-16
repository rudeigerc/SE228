package bookstore.action.auth;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.service.AppService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by rudeigerc on 2017/7/17.
 */
public class UpdatePasswordAction extends BaseAction {

    private String originalRawPassword;

    private String newRawPassword;

    private AppService appService;

    public String getOriginalRawPassword() {
        return originalRawPassword;
    }

    public void setOriginalRawPassword(String originalRawPassword) {
        this.originalRawPassword = originalRawPassword;
    }

    public String getNewRawPassword() {
        return newRawPassword;
    }

    public void setNewRawPassword(String newRawPassword) {
        this.newRawPassword = newRawPassword;
    }


    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = appService.getUserByUsername(username);
        if (!originalRawPassword.equals(user.getPassword())) {
            return ERROR;
        } else {
            user.setPassword(newRawPassword);
            appService.updateUser(user);
            return SUCCESS;
        }
    }
}
