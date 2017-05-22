package bookstore.service.impl;

import bookstore.model.User;
import bookstore.service.AppService;
import bookstore.dao.UserDao;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public class AppServiceImpl implements AppService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Integer addUser(User user) {
        return userDao.save(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public User getUserByUid(int uid) {
        return userDao.getUserByUid(uid);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
