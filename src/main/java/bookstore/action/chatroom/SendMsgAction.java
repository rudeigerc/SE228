package bookstore.action.chatroom;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.service.GroupChatService;
import bookstore.service.UserService;
import com.google.gson.JsonObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class SendMsgAction extends BaseAction {

    private String json;

    private String text;

    private UserService userService;

    private GroupChatService groupChatService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void setGroupChatService(GroupChatService groupChatService) {
        this.groupChatService = groupChatService;
    }
    @Override
    public String execute() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.getUserByUsername(username);
        int userId = user.getUid();

        groupChatService.sendGroupChat(userId, new Date(), text);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "success");
        jsonObject.addProperty("sender", username);
        json = jsonObject.toString();
        return SUCCESS;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
