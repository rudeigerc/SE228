package bookstore.action.userInfo;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.model.UserInfo;
import bookstore.service.AppService;
import com.google.gson.Gson;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rudeigerc on 2017/7/17.
 */
public class GetUserInfoAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    private String json;

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = appService.getUserByUsername(username);
        UserInfo userInfo = appService.getUserInfoByUid(user.getUid());
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userInfo", userInfo);
        map.put("username", username);
        map.put("email", user.getEmail());
        map.put("phone", user.getPhone());
        json = gson.toJson(map);
        return SUCCESS;
    }
}
