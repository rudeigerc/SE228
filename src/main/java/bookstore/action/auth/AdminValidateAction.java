package bookstore.action.auth;

import bookstore.action.BaseAction;
import bookstore.auth.AdminCallbackHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

public class AdminValidateAction extends BaseAction {

    private String username;
    private String password;
    private String json;

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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String execute() throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        System.setProperty("java.security.auth.login.config", "/Users/rudeigerc/Developer/bookstore/src/main/java/bookstore/auth/admin.login.conf");
        LoginContext context = new LoginContext("Admin", new AdminCallbackHandler(username, password.toCharArray()));
        try {
            context.login();
            result.put("status", "success");
            context.logout();
        } catch (LoginException e) {
            result.put("status", "failed");
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        json = gson.toJson(result);
        return SUCCESS;
    }


}
