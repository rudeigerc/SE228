package bookstore.action.chatroom;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.service.GroupChatService;
import bookstore.service.UserService;
import com.google.gson.JsonObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GetGroupChatAction extends BaseAction {

    private int lastChat;

    private String json;

    private UserService userService;

    private GroupChatService groupChatService;


    public void setLastChat(int lastChat) {
        this.lastChat = lastChat;
    }

    public int getLastChat() {
        return this.getLastChat();
    }

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
        User userInfo = userService.getUserByUsername(username);
        int uid = userInfo.getUid();

        int length = groupChatService.getGroupChat().size();
        if(lastChat == -1) lastChat = length - 1;
        String resultList = groupChatService.getGroupChatChunk(lastChat);
        lastChat = lastChat >= 10 ? lastChat - 10 : -2;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", resultList);
        jsonObject.addProperty("currentUser", uid);
        jsonObject.addProperty("lastChat", lastChat);
        json = jsonObject.toString();
        return SUCCESS;
    }


}
