package bookstore.socket;

import bookstore.dao.UserDao;
import bookstore.model.Message;
import bookstore.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatroomWebSocketHandler implements WebSocketHandler {

    private static final Map<Integer, WebSocketSession> users = new HashMap<Integer, WebSocketSession>();;
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("Connection Established");
        int userId = getUid(webSocketSession);
        if (users.get(userId) == null) {
            users.put(userId, webSocketSession);
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if (webSocketMessage.getPayloadLength() == 0) return;

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userDao.getUserByUsername(username);
        int uid = user.getUid();

        Message message= new Gson().fromJson(webSocketMessage.getPayload().toString(), Message.class);
        message.setFrom(uid);
        message.setFromName(username);
        message.setDate(new Date());

        sendMessage(uid, new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(message)));
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) { webSocketSession.close(); }
        users.remove(getUid(webSocketSession));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        users.remove(getUid(webSocketSession));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public boolean sendMessage(int sender, TextMessage message) {
        for (User user : userDao.getAllUsers()) {
            if(user.getUid() != sender) {
                WebSocketSession session = users.get(user.getUid());
                System.out.println("sendMessage:" + session);
                if (!session.isOpen()) return false;
                try {
                    session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    private int getUid(WebSocketSession session) {
        try {
            return (Integer) session.getAttributes().get("uid");
        } catch (Exception e) {
            return -1;
        }
    }
}
