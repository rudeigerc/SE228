package bookstore.dao;

import bookstore.model.GroupChat;

import java.util.List;

public interface GroupChatDao {

    void addGroupChat(GroupChat groupChat);
    void deleteGroupChat(GroupChat groupChat);
    void updateGroupChat(GroupChat groupChat);
    GroupChat getGroupChat();

}
