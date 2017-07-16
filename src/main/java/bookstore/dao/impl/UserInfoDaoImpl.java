package bookstore.dao.impl;

import bookstore.dao.UserInfoDao;
import bookstore.model.UserInfo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.List;


/**
 * Created by rudeigerc on 2017/7/17.
 */
public class UserInfoDaoImpl implements UserInfoDao {

    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addUserInfo(UserInfo userInfo) {
        mongoTemplate.insert(userInfo, "UserInfo");
    }

    public void deleteUserInfo(UserInfo userInfo) {
        Query query = new Query();
        query.addCriteria(new Criteria("uid").is(userInfo.getUid()));
        mongoTemplate.findAndRemove(query, UserInfo.class,"UserInfo");
    }

    public void updateUserInfo(UserInfo userInfo) {
        Query query = new Query();
        query.addCriteria(new Criteria("uid").is(userInfo.getUid()));
        Update update = new Update();
        update.set("avatar", userInfo.getAvatar());
        update.set("name", userInfo.getName());
        update.set("address", userInfo.getAddress());
        mongoTemplate.updateFirst(query, update, UserInfo.class,"UserInfo");
    }

    public UserInfo getUserInfoByUid(int uid) {
        Query query = new Query();
        query.addCriteria(new Criteria("uid").is(uid));
        return mongoTemplate.findOne(query, UserInfo.class,"UserInfo");
    }

    public List<UserInfo> getAllUserInfos() {
        return mongoTemplate.findAll(UserInfo.class, "UserInfo");
    }
}
