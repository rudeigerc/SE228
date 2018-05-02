package bookstore.service.impl;

import bookstore.dao.GroupChatDao;
import bookstore.dao.UserDao;
import bookstore.dao.UserInfoDao;
import bookstore.model.GroupChat;
import bookstore.model.Message;
import bookstore.service.GroupChatService;
import bookstore.socket.ChatroomWebSocketHandler;
import bookstore.socket.ConvertUtil;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;
import java.util.Date;

public class GroupChatServiceImpl implements GroupChatService {

    private UserDao userDao;
    private UserInfoDao userInfoDao;
    private GroupChatDao groupChatDao;
    private ChatroomWebSocketHandler socketHandler;
    private ConvertUtil convertUtil;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    public void setGroupChatDao(GroupChatDao groupChatDao) {
        this.groupChatDao = groupChatDao;
    }

    public void setSocketHandler(ChatroomWebSocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    public void setConvertUtil(ConvertUtil convertUtil) {
        this.convertUtil = convertUtil;
    }

    @Override
    public int sendGroupChat(int uid, Date datetime, String content) {

        String username = userDao.getUserByUid(uid).getUsername();
        String avatar = userInfoDao.getUserInfoByUid(uid).getAvatar();
        GroupChat groupChat = groupChatDao.getGroupChat();
        Message msg = new Message(uid, username, avatar, content, new Date());

        if(groupChat == null){
            groupChatDao.addGroupChat(new GroupChat(new ArrayList<String>(), "bookstore"));
            groupChat = groupChatDao.getGroupChat();
        }
        ArrayList<String> contents = groupChat.getContents();
        JsonObject json = new JsonObject();
        json.addProperty("uid", uid);
        json.addProperty("datetime", datetime.toString());
        json.addProperty("content", content);
        contents.add(json.toString());
        groupChat.setContents(contents);
        groupChatDao.updateGroupChat(groupChat);

        socketHandler.sendMessage(uid, new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
        return 0;
    }

    @Override
    public ArrayList<String> getGroupChat() {
        ArrayList<String> result = new ArrayList<String>();
        GroupChat groupChat = groupChatDao.getGroupChat();
        if (groupChat != null) {
            result = groupChat.getContents();
        }
        return result;
    }

    @Override
    public String getGroupChatChunk(int lastChat) {
        ArrayList<String> resultList = new ArrayList<String>();
        ArrayList<String> contents = getGroupChat();
        int iterator;
        for(iterator = lastChat; (iterator > lastChat - 10) && (iterator >= 0); iterator--) {
            resultList.add(convertUtil.formMessage(contents.get(iterator)));
        }
        return resultList.toString();
    }

}
