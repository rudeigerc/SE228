package bookstore.dao.impl;

import bookstore.dao.GroupChatDao;
import bookstore.model.GroupChat;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class GroupChatDaoImpl implements GroupChatDao {

    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void addGroupChat(GroupChat groupChat) {
        mongoTemplate.insert(groupChat, "GroupChat");
    }

    @Override
    public void deleteGroupChat(GroupChat groupChat) {
        Query query = new Query();
        query.addCriteria(new Criteria("identifier").is("bookstore"));
        mongoTemplate.findAndRemove(query, GroupChat.class,"GroupChat");
    }

    @Override
    public void updateGroupChat(GroupChat groupChat) {
        Query query = new Query();
        query.addCriteria(new Criteria("identifier").is("bookstore"));
        Update update = new Update();
        update.set("contents", groupChat.getContents());
        mongoTemplate.updateFirst(query, update, GroupChat.class,"GroupChat");
    }

    @Override
    public GroupChat getGroupChat() {
        Query query = new Query();
        query.addCriteria(new Criteria("identifier").is("bookstore"));
        return mongoTemplate.findOne(query, GroupChat.class,"GroupChat");
    }
}
