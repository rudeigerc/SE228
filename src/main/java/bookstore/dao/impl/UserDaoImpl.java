package bookstore.dao.impl;

import bookstore.dao.UserDao;
import bookstore.model.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    public Integer save(User user) {
        Integer uid = (Integer) getHibernateTemplate().save(user);
        return uid;
    }

    public void delete(User user) {
        getHibernateTemplate().delete(user);
    }

    public void update(User user) {
        getHibernateTemplate().merge(user);
    }

    public User getUserByUid(int uid) {
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) getHibernateTemplate().find("from User as user where user.uid=?", uid);
        User user = users.size() > 0 ? users.get(0) : null;
        return user;
    }

    public User getUserByUsername(String username) {
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) getHibernateTemplate().find("from User as user where user.username=?", username);
        User user = users.size() > 0 ? users.get(0) : null;
        return user;
    }

    public List<User> getAllUsers() {
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) getHibernateTemplate().find("from User");
        return users;
    }

    public List<String> getAllUsernames() {
        @SuppressWarnings("unchecked")
        List<String> usernames = (List<String>) getHibernateTemplate().find("select username from User");
        return usernames;
    }
}
