package bookstore.service;

import java.util.ArrayList;
import java.util.Date;

public interface GroupChatService {

    int sendGroupChat(int uid, Date datetime, String content);
    ArrayList<String> getGroupChat();
    String getGroupChatChunk(int lastChat);

}
